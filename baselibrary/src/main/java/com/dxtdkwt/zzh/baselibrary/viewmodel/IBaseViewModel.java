package com.dxtdkwt.zzh.baselibrary.viewmodel;

public interface IBaseViewModel<V> {

    void attachUI(V view);

    V getPageView();

    boolean isUIAttached();

    void detachUI();

}
