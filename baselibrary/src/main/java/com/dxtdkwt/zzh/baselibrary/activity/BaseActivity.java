package com.dxtdkwt.zzh.baselibrary.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.dxtdkwt.zzh.baselibrary.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * BaseActivity 所有页面都需要继承与它
 *
 * @author xuaohui
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        initView();
        initData();
    }

    /**
     * 获取对应的布局文件
     *
     * @return
     */
    public abstract @LayoutRes
    int getLayoutId();

    /**
     * 数据的初始化
     */
    public abstract void initData();

    /**
     * 控件的初始化
     */
    public abstract void initView();
}
