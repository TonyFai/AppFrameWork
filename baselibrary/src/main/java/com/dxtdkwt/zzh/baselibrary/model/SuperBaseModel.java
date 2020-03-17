package com.dxtdkwt.zzh.baselibrary.model;


import android.os.Handler;
import android.os.Looper;

import com.dxtdkwt.zzh.baselibrary.config.BaseConfig;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.reactivex.disposables.CompositeDisposable;

public abstract class SuperBaseModel<T> {
    private Handler mUiHandler = new Handler(Looper.getMainLooper());
    private CompositeDisposable compositeDisposable;
    private ReferenceQueue<IBaseModelListener> mReferenceQueue;
    private ConcurrentLinkedQueue<WeakReference<IBaseModelListener>> mWeakListenerArrayList;
    private BaseCachedData<T> mCachedData;

    protected interface IBaseModelListener {
    }
}
