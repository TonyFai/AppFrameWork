package com.dxtdkwt.zzh.networklibrary;


import com.dxtdkwt.zzh.networklibrary.download.DownloadRetrofit;
import com.dxtdkwt.zzh.networklibrary.http.GlobalRxHttp;
import com.dxtdkwt.zzh.networklibrary.http.SingleRxHttp;
import com.dxtdkwt.zzh.networklibrary.upload.UploadRetrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


public class RxHttpUtils {
    private static RxHttpUtils instance;

    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                }
            }

        }
        return instance;
    }

    public GlobalRxHttp config() {
        return GlobalRxHttp.getInstance();
    }


    /**
     * 使用全局参数创建请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public static <K> K createApi(Class<K> cls) {
        return GlobalRxHttp.createGApi(cls);
    }

    /**
     * 获取单个请求配置实例
     *
     * @return
     */
    public static SingleRxHttp getSInstance() {
        return SingleRxHttp.getInstance();
    }


    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @return
     */
    public static Observable<Object> uploadImg(String filePath, String name) {
        return UploadRetrofit.uploadImg(filePath, name);

    }


}
