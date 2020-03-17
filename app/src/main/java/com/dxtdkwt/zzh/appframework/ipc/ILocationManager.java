package com.dxtdkwt.zzh.appframework.ipc;

import com.example.ipc.annotation.ServiceId;

@ServiceId("LocationManager")
public interface ILocationManager {

    Location getLocation();
}
