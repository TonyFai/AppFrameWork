package com.dxtdkwt.zzh.networklibrary;

import android.annotation.SuppressLint;
import android.app.Application;

import com.dxtdkwt.zzh.baselibrary.utils.Util;


public class NetworkApp {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    public static void init(Application application) {
        sApplication = application;
        Util.init(sApplication);
    }

    public static Application getApp() {
        return sApplication;
    }


}
