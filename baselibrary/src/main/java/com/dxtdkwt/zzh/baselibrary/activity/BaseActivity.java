package com.dxtdkwt.zzh.baselibrary.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dxtdkwt.zzh.baselibrary.loadsir.ErrorCallback;
import com.dxtdkwt.zzh.baselibrary.loadsir.LoadingCallback;
import com.dxtdkwt.zzh.baselibrary.view.IBaseView;
import com.dxtdkwt.zzh.baselibrary.viewmodel.IBaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * BaseActivity 所有页面都需要继承与它
 *
 * @author xuaohui
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends IBaseViewModel> extends RxAppCompatActivity implements IBaseView {

    protected VM viewModel;
    private LoadService mLoadService;
    protected V viewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        performDataBinding();
        initView();
        initData();
    }

    private void initViewModel() {
        viewModel = getViewModel();
        if (viewModel != null) {
            viewModel.attachUI(this);
        }
    }

    @Override
    public void onRefreshFailure(String message) {
        if (mLoadService != null) {
            mLoadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void showLoading() {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showContent() {
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }

    public void setLoadSir(View view) {
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    protected abstract void onRetryBtnClick();

    protected abstract VM getViewModel();

    public abstract int getBindingVariable();

    /**
     * 获取对应的布局文件
     *
     * @return
     */
    public abstract @LayoutRes
    int getLayoutId();

    private void performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModel);
        }
        viewDataBinding.executePendingBindings();
    }

    /**
     * 数据的初始化
     */
    public abstract void initData();

    /**
     * 控件的初始化
     */
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null && viewModel.isUIAttached()) {
            viewModel.detachUI();
        }
    }
}
