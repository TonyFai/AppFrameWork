package com.dxtdkwt.zzh.baselibrary.model;

import java.lang.ref.WeakReference;

public abstract class BaseModel<T> extends SuperBaseModel<T> {

    /**
     * 加载网络数据成功
     * 通知所有的注册者加载结果
     *
     * @param data
     */
    protected void loadSuccess(final T data) {
        synchronized (this) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                        if (weakListener.get() instanceof IModelListener) {
                            IModelListener listenerItem = (IModelListener) weakListener.get();
                            if (listenerItem != null) {
                                listenerItem.onLoadFinish(BaseModel.this, data);
                            }
                        }
                    }
                    if (getCachedPreferenceKey() != null) {
                        saveDataToPreference(data);
                    }
                }
            }, 0);
        }
    }

    /**
     * 加载失败的情况
     *
     * @param prompt
     */
    protected void loadFail(final String prompt) {
        synchronized (this) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (WeakReference<IBaseModelListener> weakReference : mWeakListenerArrayList) {
                        if (weakReference.get() instanceof IModelListener) {
                            IModelListener listenerItem = (IModelListener) weakReference.get();
                            if (listenerItem != null) {
                                listenerItem.onLoadFail(BaseModel.this, prompt);
                            }
                        }
                    }
                }
            }, 0);
        }
    }


    @Override
    protected void notifyCachedData(T data) {
        loadSuccess(data);
    }

    public interface IModelListener<T> extends SuperBaseModel.IBaseModelListener {
        /**
         * 加载结束
         *
         * @param model 对应的model
         * @param data  数据
         */
        void onLoadFinish(BaseModel model, T data);

        /**
         * 数据加载错误
         *
         * @param model  对应的model
         * @param prompt 错误信息
         */
        void onLoadFail(BaseModel model, String prompt);
    }
}
