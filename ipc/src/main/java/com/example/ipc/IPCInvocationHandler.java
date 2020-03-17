package com.example.ipc;

import com.example.ipc.model.Request;
import com.example.ipc.model.Respson;
import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IPCInvocationHandler implements InvocationHandler {

    private final Class<? extends IPCService> service;
    private final String serviceId;
    static Gson gson = new Gson();

    public IPCInvocationHandler(Class<? extends IPCService> service, String serviceId) {
        this.service = service;
        this.serviceId = serviceId;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Respson send = Channel.getInstance().send(Request.GET_METHOD, service, serviceId, method.getName(), args);
        if (send.isSuccess()){
            //拿到返回的返回值的类型
            Class<?> returnType = method.getReturnType();
            if (returnType != void.class && returnType != Void.class){
                String source = send.getSource();
                //通过gson将参数转成对应的返回值
                return gson.fromJson(source,returnType);
            }
        }
        return null;
    }
}
