package com.dxtdkwt.zzh.appframework.ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dxtdkwt.zzh.utilslibrary.LogUtil
import com.example.ipc.IPC

class GpsService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        LocationManager.getInstance().location = Location("岳麓区天之道", 1.1, 2.2)

        IPC.regiest(LocationManager::class.java)
        LogUtil.i("渣渣辉",LocationManager.getInstance().location.toString());
    }
}
