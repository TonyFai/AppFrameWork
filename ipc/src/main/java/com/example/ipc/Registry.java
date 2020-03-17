package com.example.ipc;

import com.dxtdkwt.zzh.baselibrary.utils.LogUtils;
import com.example.ipc.annotation.ServiceId;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Registry {

    private static volatile Registry sInstance;

    //记录服务表
    private ConcurrentHashMap<String, Class<?>> mServiceMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Class<?>, Map<String, Method>> mMethodMap = new ConcurrentHashMap<>();

    /**
     * 保存实例对象
     *
     * @return
     */
    private ConcurrentHashMap<String, Object> mObjectMap = new ConcurrentHashMap<>();

    public static Registry getInstance() {
        if (sInstance == null) {
            synchronized (Registry.class) {
                if (sInstance == null) {
                    sInstance = new Registry();
                }
            }
        }
        return sInstance;
    }

    private Registry() {
    }

    void registered(Class<?> server) {
        ServiceId annotation = server.getAnnotation(ServiceId.class);
        if (null == annotation) {
            throw new RuntimeException("使用带ServicedId的进行绑定");
        }
        //这里存储两个表   一个为方法表，一个为服务表
        String value = annotation.value();
        mServiceMap.put(value, server);

        Method[] methods = server.getMethods();
        Map<String, Method> methodMap = new HashMap<>();
        for (Method method : methods) {
            String name = method.getName();
            /**
             * 防止方法的重载导致程序不知道调用那个，使用方法名加参数列表的组合
             */
            StringBuilder sb = new StringBuilder(name);
            sb.append("(");
            //获取参数的类型
            Class<?>[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length != 0) {
                sb.append(parameterTypes[0].getName());
            }
            for (int j = 1; j < parameterTypes.length; j++) {
                sb.append(",");
                sb.append(parameterTypes[j].getName());
            }
            sb.append(")");
            methodMap.put(sb.toString(), method);
        }
        //将方法进行存储
        mMethodMap.put(server, methodMap);

        LogUtils.i("渣渣辉", "服务表：" + mServiceMap.toString() + "\n" + "方法表:" + mMethodMap.toString());
    }

    Class<?> getService(String serviceId) {
        return mServiceMap.get(serviceId);
    }

    /**
     * 拿去数据，要拼接参数
     *
     * @param serviceId
     * @param methodName
     * @param parameters
     * @return
     */
    Method getMethod(String serviceId, String methodName, Object[] parameters) {
        Class<?> aClass = mServiceMap.get(serviceId);
        Map<String, Method> methodMap = mMethodMap.get(aClass);

        /**
         * 防止方法的重载导致程序不知道调用那个，使用方法名加参数列表的组合
         */
        StringBuilder sb = new StringBuilder(methodName);
        sb.append("(");
        //获取参数的类型
        if (parameters.length != 0) {
            sb.append(parameters[0].getClass().getName());
        }
        for (int j = 1; j < parameters.length; j++) {
            sb.append(",");
            sb.append(parameters[j].getClass().getName());
        }
        sb.append(")");

        Method method = null;
        if (methodMap != null) {
            method = methodMap.get(sb.toString());
        }

        return method;
    }

    void putObject(String serviceId, Object object) {
        mObjectMap.put(serviceId, object);
    }

    Object getObject(String serviceId) {
        return mObjectMap.get(serviceId);
    }
}
