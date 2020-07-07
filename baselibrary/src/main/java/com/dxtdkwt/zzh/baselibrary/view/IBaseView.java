package com.dxtdkwt.zzh.baselibrary.view;

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
     * 展示空占位图
     */
    void onRefreshEmpty();

    /**
     * 刷新失败
     *
     * @param message 失败原因
     */
    void onRefreshFailure(String message);
}
