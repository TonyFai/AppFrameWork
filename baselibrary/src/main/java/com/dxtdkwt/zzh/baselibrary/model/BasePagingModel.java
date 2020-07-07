package com.dxtdkwt.zzh.baselibrary.model;

import java.lang.ref.WeakReference;

public abstract class BasePagingModel<T> extends SuperBaseModel<T> {

    protected boolean isRefresh = true;
    protected int pageNumber = 0;

    protected void loadSucceed(final T data, final boolean isEmpty, final boolean isFirstPage, final boolean hasNextPage) {
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (WeakReference<IBaseModelListener> weakReference : mWeakListenerArrayList) {
                    if (weakReference.get() instanceof IModelListener) {
                        IModelListener modeItem = (IModelListener) weakReference.get();
                        if (modeItem != null) {
                            modeItem.onLoadFinish(BasePagingModel.this, data, isEmpty, isFirstPage, hasNextPage);
                        }
                    }
                }
                if (getCachedPreferenceKey() != null && isFirstPage) {
                    saveDataToPreference(data);
                }
            }
        }, 0);
    }

    protected void loadFail(final String prompt, final boolean isFirstPage) {
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (WeakReference<IBaseModelListener> weakReference : mWeakListenerArrayList) {
                    if (weakReference.get() instanceof IModelListener) {
                        IModelListener modeItem = (IModelListener) weakReference.get();
                        if (modeItem != null) {
                            modeItem.onLoadFail(BasePagingModel.this, prompt, isFirstPage);
                        }
                    }
                }
            }
        }, 0);
    }


    @Override
    protected void notifyCachedData(T data) {

    }

    public interface IModelListener<T> extends IBaseModelListener {
        void onLoadFinish(BasePagingModel model, T data, boolean isEmpty, boolean isFirstPage, boolean hasNextPage);

        void onLoadFail(BasePagingModel model, String prompt, boolean isFirstPage);
    }
}
