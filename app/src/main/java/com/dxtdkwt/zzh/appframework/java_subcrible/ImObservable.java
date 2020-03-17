package com.dxtdkwt.zzh.appframework.java_subcrible;

import java.util.ArrayList;
import java.util.List;

public class ImObservable implements Observable {

    List<Observer> mList = new ArrayList<>();

    private Object mObject;

    public void pushMessage(Object object) {
        System.out.println("发送通知了");
        mObject = object;
        notifyObservable();
    }

    @Override
    public void add(Observer object) {
        mList.add(object);
    }

    @Override
    public void remove(Observer object) {
        mList.remove(object);
    }

    @Override
    public void notifyObservable() {
        for (int i = 0; i < mList.size(); i++) {
            Observer o = mList.get(i);
            o.updata(mObject);
        }
    }
}
