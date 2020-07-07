package com.example.servicemanagerlibrary;

import android.os.SystemClock;

public class ServiceManager {
    public static void main(String[] args) {
        long l = SystemClock.uptimeMillis();
        System.out.println("时间为：" + l);
    }
}
