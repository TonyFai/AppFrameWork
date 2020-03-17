package com.dxtdkwt.zzh.appframework.ipc;

import android.util.Log;

import com.example.ipc.annotation.ServiceId;

@ServiceId("LocationManager")
public class LocationManager {

    private static volatile LocationManager sInstance;

    public static LocationManager getInstance() {
        if (sInstance == null){
            synchronized (LocationManager.class){
                if (sInstance == null){
                    sInstance = new LocationManager();
                }
            }
        }
        return sInstance;
    }

    private Location sLocation;

    public Location getLocation() {
        return sLocation;
    }

    public void setLocation(Location location) {
        sLocation = location;
    }

}
