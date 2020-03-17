package com.dxtdkwt.zzh.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionManager {

    private static final int REQUEST_PERMISSION = 401;

    private static final String WRITE_ExERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public boolean checkSelfPermission(Context context, String[] permission) {
        for (String per : permission) {
            if (!(ActivityCompat.checkSelfPermission(context, per) == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    public void requestPermission(Activity context, String[] permission) {
        ActivityCompat.requestPermissions(context, permission, REQUEST_PERMISSION);
    }

}
