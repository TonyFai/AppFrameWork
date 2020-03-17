package com.example.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.example.ipc.model.Parameters;
import com.example.ipc.model.Request;
import com.example.ipc.model.Respson;
import com.google.gson.Gson;

import java.util.concurrent.ConcurrentHashMap;

public class Channel {

    private static volatile Channel sInstance;

    static Gson gson = new Gson();

    //已经绑定过的
    private ConcurrentHashMap<Class<? extends IPCService>, Boolean> mBinds =
            new ConcurrentHashMap<>();
    //正在绑定的
    private ConcurrentHashMap<Class<? extends IPCService>, Boolean> mBinding =
            new ConcurrentHashMap<>();
    //已经绑定的服务对应的ServiceConnect
    private final ConcurrentHashMap<Class<? extends IPCService>, IPCServiceConnection> mServiceConnections =
            new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends IPCService>, IIPCService> mBinders =
            new ConcurrentHashMap<>();

    private ConcurrentHashMap<Class<? extends IPCService>, IIPCService> binders = new ConcurrentHashMap<>();

    public static Channel getInstance() {
        if (sInstance == null) {
            synchronized (Channel.class) {
                if (sInstance == null) {
                    sInstance = new Channel();
                }
            }
        }
        return sInstance;
    }

    void bind(Context context, String packageName, Class<? extends IPCService> service) {
        IPCServiceConnection ipcServiceConnection;
        //是否已经绑定
        Boolean isBound = mBinds.get(service);
        if (isBound != null && isBound) {
            return;
        }
        //是否正在绑定
        Boolean isBinding = mBinding.get(service);
        if (isBinding != null && isBinding) {
            return;
        }
        //要绑定了
        mBinding.put(service, true);
        ipcServiceConnection = new IPCServiceConnection(service);
        mServiceConnections.put(service, ipcServiceConnection);

        Intent intent;
        if (!TextUtils.isEmpty(packageName)) {
            intent = new Intent();
            intent.setClassName(packageName, service.getName());
        } else {
            intent = new Intent(context, service);
        }

        context.bindService(intent, new IPCServiceConnection(service), Context.BIND_AUTO_CREATE);
    }

    public void unbind(Context context,  Class<? extends IPCService> service) {
        Boolean bound = mBinds.get(service);
        if (bound != null && bound) {
            IPCServiceConnection connection = mServiceConnections.get(service);
            if (connection != null) {
                context.unbindService(connection);
            }
            mBinds.put(service, false);
        }
    }

    class IPCServiceConnection implements ServiceConnection {

        private final Class<? extends IPCService> mService;

        public IPCServiceConnection(Class<? extends IPCService> service) {
            mService = service;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IIPCService bind = IIPCService.Stub.asInterface(service);
            mBinders.put(mService, bind);
            mBinds.put(mService, true);
            mBinding.remove(mService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinders.remove(mService);
            mBinds.remove(mService);
        }
    }

    Respson send(int type, Class<? extends IPCService> ipcService, String serviceId, String method, Object[] parameters) {
        Parameters[] parameters1 = makeParameters(parameters);
        Request request = new Request(type, serviceId, method, parameters1);
        IIPCService iipcService = mBinders.get(ipcService);
        try {
            if (iipcService != null) {
                return iipcService.send(request);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return new Respson(null, false);
    }

    private Parameters[] makeParameters(Object[] objects) {
        Parameters[] parameters;
        if (objects != null) {
            parameters = new Parameters[objects.length];
            for (int i = 0; i < objects.length; i++) {
                parameters[i] = new Parameters(objects[i].getClass().getName(),
                        gson.toJson(objects[i]));
            }
        } else {
            parameters = new Parameters[0];
        }
        return parameters;
    }

}
