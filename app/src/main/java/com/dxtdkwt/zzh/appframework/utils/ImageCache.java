package com.dxtdkwt.zzh.appframework.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.LruCache;

import com.dxtdkwt.zzh.appframework.BuildConfig;
import com.dxtdkwt.zzh.appframework.utils.disk.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ImageCache {

    private static ImageCache instance;
    private DiskLruCache diskLruCache;

    private ImageCache() {

    }

    public static ImageCache getInstance() {
        if (instance == null) {
            synchronized (ImageCache.class) {
                if (instance == null) {
                    instance = new ImageCache();
                }
            }
        }
        return instance;
    }

    private LruCache<String, Bitmap> mLruCache;
    private Set<WeakReference<Bitmap>> reusablePool;

    private void init(Context context, String dir) {

        //复用池
        reusablePool = Collections.synchronizedSet(new HashSet<WeakReference<Bitmap>>());

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取进程最大物理内存
        int memoryClass = 0;
        if (am != null) {
            memoryClass = am.getMemoryClass();
        }

        mLruCache = new LruCache<String, Bitmap>(memoryClass / 8 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //图片的大小
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return value.getAllocationByteCount();
                }
                return value.getByteCount();
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                if (oldValue.isMutable()) {
                    //3.0之前  bitmap 缓存 native
                    //<8.0 bitamp 缓存  java
                    //8.0 native
                    reusablePool.add(new WeakReference<Bitmap>(oldValue, getReferenceQueue()));
//                    getReusable()
                }
                oldValue.recycle();
            }
        };
        try {
            diskLruCache = DiskLruCache.open(new File(dir), BuildConfig.VERSION_CODE, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putBitmap2Disk(String key, Bitmap bitmap) {
        DiskLruCache.Snapshot snapshot = null;
        OutputStream os = null;
        try {
            snapshot = diskLruCache.get(key);
            if (snapshot == null) {
                DiskLruCache.Editor edit = diskLruCache.edit(key);
                if (edit != null) {
                    os = edit.newOutputStream(0);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);
                    edit.commit();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Bitmap getBitmapFromDisk(String key, Bitmap reusable) {
        DiskLruCache.Snapshot snapshot = null;
        Bitmap bitmap = null;
        try {
            snapshot = diskLruCache.get(key);
            if (snapshot == null) {
                return null;
            }
            InputStream is = snapshot.getInputStream(0);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            options.inBitmap = reusable;
            bitmap = BitmapFactory.decodeStream(is, null, options);
            if (bitmap != null) {
                mLruCache.put(key, bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (snapshot != null){
                snapshot.close();
            }
        }
        return bitmap;
    }

    private ReferenceQueue<Bitmap> mReferenceQueue;
    private boolean shutDown;

    private ReferenceQueue<Bitmap> getReferenceQueue() {
        if (mReferenceQueue == null) {
            mReferenceQueue = new ReferenceQueue<>();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while ((shutDown)) {
                        try {
                            Reference<? extends Bitmap> remove = mReferenceQueue.remove();
                            Bitmap bitmap = remove.get();
                            if (bitmap != null && !bitmap.isRecycled()) {
                                bitmap.recycle();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return mReferenceQueue;
    }

    /**
     * 将Bitmap放入内存
     *
     * @param key
     * @param bitmap
     */
    public void putBitmap2Memory(String key, Bitmap bitmap) {
        mLruCache.put(key, bitmap);
    }

    /**
     * 从内存中去除Bitmap
     *
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemory(String key) {
        return mLruCache.get(key);
    }

    /**
     * 3.0之前不能复用
     * 3.0-4.4 宽高一样，inSampleSize = 1
     * 4.4 只要小于等于就行
     *
     * @param w
     * @param h
     * @param inSampleSize
     * @return
     */
    public Bitmap getReusable(int w, int h, int inSampleSize) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return null;
        }
        Bitmap reusable = null;
        Iterator<WeakReference<Bitmap>> iterator = reusablePool.iterator();
        while (iterator.hasNext()) {
            Bitmap bitmap = iterator.next().get();
            if (bitmap != null) {
                if (checkInBitmap(bitmap, w, h, inSampleSize)) {
                    reusable = bitmap;
                    iterator.remove();
                    break;
                }
            } else {
                iterator.remove();
            }
        }
        return reusable;
    }

    /**
     * 校验Bitmap
     *
     * @param bitmap
     * @param w
     * @param h
     * @param inSampleSize
     * @return
     */
    private boolean checkInBitmap(Bitmap bitmap, int w, int h, int inSampleSize) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return bitmap.getWidth() == w && bitmap.getHeight() == h && inSampleSize == 1;
        }

        if (inSampleSize > 1) {
            w /= inSampleSize;
            h /= inSampleSize;
        }
        int byteCount = w * h * getBytesPerPixel(bitmap.getConfig());
        //图片内存  系统分配内存
        return byteCount <= bitmap.getAllocationByteCount();
    }

    /**
     * 通过像素格式计算每一个像素占用多少字节
     *
     * @param config
     * @return
     */
    private int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        return 2;
    }
}
