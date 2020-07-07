package com.dxtdkwt.zzh.baselibrary.viewmodel;

import androidx.lifecycle.ViewModel;

import com.dxtdkwt.zzh.baselibrary.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BaseViewModel<V, M extends SuperBaseModel> extends ViewModel implements IBaseViewModel<V> {

    private Reference<V> mUIRef;
    protected M model;

    @Override
    public void attachUI(V view) {
        mUIRef = new WeakReference<>(view);
    }

    @Override
    public V getPageView() {
        if (mUIRef == null) {
            return null;
        }
        return mUIRef.get();
    }

    @Override
    public boolean isUIAttached() {
        return mUIRef != null && mUIRef.get() != null;
    }

    @Override
    public void detachUI() {
        if (mUIRef != null) {
            mUIRef.clear();
            mUIRef = null;
        }
        if (model != null) {
            model.cancel();
        }
    }
}
