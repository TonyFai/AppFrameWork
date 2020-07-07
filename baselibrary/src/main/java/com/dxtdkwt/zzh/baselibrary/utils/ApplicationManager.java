package com.dxtdkwt.zzh.baselibrary.utils;

import android.content.Context;

import com.dxtdkwt.zzh.baselibrary.loadsir.CustomCallback;
import com.dxtdkwt.zzh.baselibrary.loadsir.EmptyCallback;
import com.dxtdkwt.zzh.baselibrary.loadsir.ErrorCallback;
import com.dxtdkwt.zzh.baselibrary.loadsir.LoadingCallback;
import com.dxtdkwt.zzh.baselibrary.loadsir.TimeoutCallback;
import com.dxtdkwt.zzh.baselibrary.preference.PreferencesUtil;
import com.kingja.loadsir.core.LoadSir;

public class ApplicationManager {

    private static ApplicationManager sApplicationManager;

    public static ApplicationManager getInstance() {
        return sApplicationManager;
    }

    public static void init(Context context) {
        Util.init(context);
        PreferencesUtil.init(context);
        LoadSirInit();
    }

    private static void LoadSirInit() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();

    }
}
