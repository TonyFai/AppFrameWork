package com.dxtdkwt.zzh.appframework.ipctest;

import android.os.IBinder;
import android.os.RemoteException;

import static com.dxtdkwt.zzh.appframework.ipctest.BinderPool.BINDER_COMPUTE;
import static com.dxtdkwt.zzh.appframework.ipctest.BinderPool.BINDER_SECURITY_CENTER;

public class BinderPoolImpl extends IBinderPool.Stub  {
    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null;
        switch (binderCode){
            case BINDER_SECURITY_CENTER:{
                binder = new SecurityCenterImpl();
                break;
            }
            case BINDER_COMPUTE:{
                binder = new ComputeImpl();
                break;
            }
            default:
                break;
        }
        return binder;
    }
}
