package com.dxtdkwt.zzh.baselibrary.utils;

import android.content.Context;

public class ApplicationManager {

    private static ApplicationManager sApplicationManager;

    public static ApplicationManager getInstance() {
        return sApplicationManager;
    }

    public static void init(Context context) {
        Util.init(context);
    }
}
