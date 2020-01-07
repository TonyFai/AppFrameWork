package com.dxtdkwt.zzh.appframework;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Created by  peter on 2018/7/11.
 */

public class CustomApplication extends Application {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.createAllNotificationChannels(this);
    }
}
