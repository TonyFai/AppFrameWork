package com.dxtdkwt.zzh.baselibrary.activity;

/**
 * @author Super
 * @DATE 2019/6/1215:00
 * @describe view接口
 */
public interface IBaseView {
    /**
     * 展示内容
     */
    void showContent();

    /**
     * 展示正在加载
     */
    void showLoading();

    /**
     *
     */
    void onRefreshEmpty();
}
