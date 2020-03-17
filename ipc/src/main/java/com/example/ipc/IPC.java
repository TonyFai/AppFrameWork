package com.example.ipc;

import android.content.Context;

import com.example.ipc.annotation.ServiceId;
import com.example.ipc.model.Request;
import com.example.ipc.model.Respson;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Proxy;

public class IPC {

    public static void regiest(@NotNull Class<?> server) {
        Registry.getInstance().registered(server);
    }

    public static void bind(Context context, Class<? extends IPCService> ipcService) {
        Channel.getInstance().bind(context, null, ipcService);
    }

    public static void bind(Context context, String packageName, Class<? extends IPCService> ipcService) {
        Channel.getInstance().bind(context, packageName, ipcService);
    }

    public static <T> T getInstance(Class<? extends IPCService> service, String methodName, Class<T> classType, Object... parameters) {
        if (!classType.isInterface()) {
            throw new RuntimeException("错误，必须要使用接口");
        }
        ServiceId annotation = classType.getAnnotation(ServiceId.class);
        Respson send = Channel.getInstance().send(Request.GET_INSTANCE, service, annotation.value(), methodName, parameters);

        if (send.isSuccess()) {
            return (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class[]{classType}, new IPCInvocationHandler(service, annotation.value()));
        }
        return null;
    }


}
