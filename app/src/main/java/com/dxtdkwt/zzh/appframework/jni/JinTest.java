package com.dxtdkwt.zzh.appframework.jni;

public class JinTest {

    static {
        System.loadLibrary("native-lib");
    }

    public native String sayHello(String string);

    public static void main(String[] args) {
        JinTest jinTest = new JinTest();
        String str = jinTest.sayHello("你好你好");
        System.out.println("从jni返回的数据为："+str);
    }

}
