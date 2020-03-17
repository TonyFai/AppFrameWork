package com.dxtdkwt.zzh.appframework.java_subcrible;

public interface Observable {
    /**
     * 添加观察者
     *
     * @param object
     */
    void add(Observer object);

    /**
     * 移除观察者
     *
     * @param object
     */
    void remove(Observer object);

    /**
     * 发送通知
     */
    void notifyObservable();
}
