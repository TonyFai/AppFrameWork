package com.example.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.ipc.model.Parameters;
import com.example.ipc.model.Request;
import com.example.ipc.model.Respson;
import com.google.gson.Gson;

import java.lang.reflect.Method;

public abstract class IPCService extends Service {

    static Gson gson = new Gson();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IIPCService.Stub() {
            @Override
            public Respson send(Request request) {
                int requestType = request.getRequestType();

                String methodName = request.getMethod();
                String serverId = request.getServerId();
                Parameters[] parameters = request.getParameters();
                Object[] objects = restoreParameters(parameters);

                Method method = Registry.getInstance().getMethod(serverId, methodName, objects);

                Respson respson = null;
                switch (requestType) {
                    case Request.GET_INSTANCE:
                        try {
                            Object invoke = method.invoke(null, objects);
                            //保存静态返回的实例对象 方便调用本地方法时使用
                            Registry.getInstance().putObject(serverId, invoke);
                            respson = new Respson(null, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            respson = new Respson(null, false);
                        }
                        break;
                    case Request.GET_METHOD:
                        Object object = Registry.getInstance().getObject(serverId);
                        try {
                            Object invoke = method.invoke(object, objects);
                            respson = new Respson(gson.toJson(invoke), true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            respson = new Respson(null, false);
                        }
                        break;
                    default:
                        respson = new Respson(null, false);
                        break;
                }
                return respson;
            }
        };
    }

    protected Object[] restoreParameters(Parameters[] parameters) {
        Object[] objects = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameters parameter = parameters[i];
            //还原
            try {
                objects[i] = gson.fromJson(parameter.getValue(), Class.forName(parameter.getType()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }

    public static class IPCService0 extends IPCService{}


    public static class IPCService1 extends IPCService {
    }

    public static class IPCService2 extends IPCService {
    }
}
