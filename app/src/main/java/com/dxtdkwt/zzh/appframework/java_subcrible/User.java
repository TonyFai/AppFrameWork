package com.dxtdkwt.zzh.appframework.java_subcrible;

public class User implements Observer {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void updata(Object str) {
        System.out.println(name+"接收到的数据为："+str);
    }
}
