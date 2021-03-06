package com.dxtdkwt.zzh.appframework;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.dxtdkwt.zzh.appframework.crash.CrashHandler;
import com.dxtdkwt.zzh.baselibrary.BaseApplication;
import com.dxtdkwt.zzh.networklibrary.NetworkApp;
import com.dxtdkwt.zzh.utilslibrary.LogUtil;

/**
 * Created by  peter on 2018/7/11.
 */
public class CustomApplication extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.createAllNotificationChannels(this);
        NetworkApp.init(this);

        setIsIsDebug(BuildConfig.DEBUG);
        if (BuildConfig.isModule) {
            LogUtil.i("Logg", "集成化项目");
        } else {
            LogUtil.i("Logg", "组件化项目");
//            throw new RuntimeException("组件化项目,主app 不可单独运行");
        }
        String serverUrl = BuildConfig.serverUrl;
        LogUtil.i("Logg", "环境的路径" + serverUrl);

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
