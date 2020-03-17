package com.dxtdkwt.zzh.appframework.java_subcrible;

public class Clinet {
    public static void main(String[] args) {

        ImObservable imObservable = new ImObservable();
        imObservable.add(new User("AV"));
        imObservable.add(new User("Zero"));
        imObservable.add(new User("Lean"));
        imObservable.pushMessage("发送了一个消息");
    }
}
