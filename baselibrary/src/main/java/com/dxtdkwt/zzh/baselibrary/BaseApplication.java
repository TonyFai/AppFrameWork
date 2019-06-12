package com.dxtdkwt.zzh.baselibrary;

import android.app.Application;

/**
 * @author Super
 * @DATE 2019/6/1214:54
 * @describe
 */
public class BaseApplication extends Application {

    public static BaseApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
