package com.dxtdkwt.zzh.networklibrary.util;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * @author star
 */
public class Util {


    private static final String TAG = "Utils";

    private static Context context;

    private Util() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        Util.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }





}

