package com.dxtdkwt.zzh.appframework.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Config;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.airbnb.lottie.L;
import com.dxtdkwt.zzh.appframework.R;
import com.dxtdkwt.zzh.appframework.databinding.DataTestActivityBinding;
import com.dxtdkwt.zzh.baselibrary.activity.BaseActivity;
import com.dxtdkwt.zzh.baselibrary.viewmodel.BaseViewModel;

public class DataTestActivity extends BaseActivity<DataTestActivityBinding, BaseViewModel> {
    @Override
    protected void onRetryBtnClick() {

    }

    public void viewUrl(String url, String mimeType) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), mimeType);
        //隐士跳转的话，先要检查
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                if (Config.LOGD) {
                    Log.d("Logg", "activity not found for " + mimeType + " over " + Uri.parse(url).getScheme(), e);
                }
            }
        }
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.data_test_activity;
    }

    @Override
    public void initData() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent());
    }

    @Override
    public void initView() {

    }

    @Override
    public void onRefreshEmpty() {

    }

}
