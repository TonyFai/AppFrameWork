package com.dxtdkwt.zzh.baselibrary;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.dxtdkwt.zzh.baselibrary.config.BaseConfig;
import com.dxtdkwt.zzh.baselibrary.utils.ApplicationManager;
import com.dxtdkwt.zzh.baselibrary.utils.LogUtils;

import java.util.List;

/**
 * @author Super
 * @DATE 2019/6/1214:54
 * @describe
 */
public class BaseApplication extends Application {

    public static BaseApplication sApplication;
    private static boolean isIsDebug;

    public static boolean isIsDebug() {
        return isIsDebug;
    }

    public static void setIsIsDebug(boolean isIsDebug) {
        BaseApplication.isIsDebug = isIsDebug;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        if (BuildConfig.DEBUG) {
            LogUtils.i(BaseConfig.TAG, "这是Debug模式");
        } else {
            LogUtils.i(BaseConfig.TAG, "这是Release模式");
        }

        ApplicationManager.init(this);
    }

    public static Context getInstance() {
        return sApplication;
    }

    /**
     * 获取进程名
     */
    public static String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                return proInfo.processName;
            }
        }
        return null;
    }
}
