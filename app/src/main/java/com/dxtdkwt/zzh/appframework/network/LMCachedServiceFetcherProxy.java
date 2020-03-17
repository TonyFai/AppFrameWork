package com.dxtdkwt.zzh.appframework.network;

import android.content.Context;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Log;

import com.dxtdkwt.zzh.appframework.utils.HookHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import static com.dxtdkwt.zzh.appframework.utils.HookHelper.getField;

public class LMCachedServiceFetcherProxy implements InvocationHandler {

    private Object mLMCachedServiceFetcher;

    public LMCachedServiceFetcherProxy(Object LMCachedServiceFetcher) {
        this.mLMCachedServiceFetcher = LMCachedServiceFetcher;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //为什么拦截getService，而不是createService?
        if (TextUtils.equals(method.getName(), "getService")) {
            Object result = method.invoke(mLMCachedServiceFetcher, args);
            if (result instanceof LocationManager) {
                //在这里hook LocationManager
                HookHelper.hookLocationManager((LocationManager) result);
            }
            return result;
        }
        return method.invoke(mLMCachedServiceFetcher, args);
    }


    //HookHelper.java
    public static void hookSystemServiceRegistry() {
        try {
            Object systemServiceFetchers = null;
            Class<?> locationManagerClazsz = Class.forName("android.app.SystemServiceRegistry");
            //获取SystemServiceRegistry的SYSTEM_SERVICE_FETCHERS成员
            systemServiceFetchers = getField(locationManagerClazsz, null, "SYSTEM_SERVICE_FETCHERS");
            if (systemServiceFetchers instanceof HashMap) {
                HashMap fetchersMap = (HashMap) systemServiceFetchers;
                Object locationServiceFetcher = fetchersMap.get(Context.LOCATION_SERVICE);
                Class<?> serviceFetcheClazz = Class.forName("android.app.SystemServiceRegistry$ServiceFetcher");
                //创建代理类
                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        new Class<?>[]{serviceFetcheClazz}, new LMCachedServiceFetcherProxy(locationServiceFetcher));
                //用代理类替换掉原来的ServiceFetcher
                if (fetchersMap.put(Context.LOCATION_SERVICE, proxy) == locationServiceFetcher) {
                    Log.d("LocationTest", "hook success! ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
