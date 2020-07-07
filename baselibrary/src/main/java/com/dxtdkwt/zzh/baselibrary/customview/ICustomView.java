package com.dxtdkwt.zzh.baselibrary.customview;

public interface ICustomView<S extends BaseCustomViewModel> {
    /**
     * 设置数据
     *
     * @param data
     */
    void setData(S data);

    /**
     * 设置样式
     *
     * @param resId
     */
    void setStyle(int resId);

    /**
     * 设置页面监听
     *
     * @param listener
     */
    void setActionListener(ICustomViewActionListener listener);
}
