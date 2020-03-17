package com.dxtdkwt.zzh.appframework.java_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理模式可以在不修改被代理对象的基础上，通过扩展代理类，进行一些其他功能附加与增强
 * <p>
 * 静态代理
 */
public class ProxyHomeWork {

    public static void main(String[] args) {
        System.out.println("程序开始执行");
        Subject proxy = new Proxy(new AvTeacher());
        proxy.buy();
        System.out.println("程序结束执行");

        System.out.println("程序开始执行");
        AvTeacher avTeacher = new AvTeacher();
        DaynamicIvocationHandler ivocationHandler = new DaynamicIvocationHandler(avTeacher);
        //获取创建的对象的类加载器，和需要实现的接口
        Subject subject = (Subject) java.lang.reflect.Proxy.newProxyInstance(avTeacher.getClass().getClassLoader(), avTeacher.getClass().getInterfaces(), ivocationHandler);
//        Subject avTeacher = (Subject) java.lang.reflect.Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, ivocationHandler);
        subject.buy();
        System.out.println("程序结束执行");
    }
}

class DaynamicIvocationHandler implements InvocationHandler {

    private Object mObject;

    DaynamicIvocationHandler(Object object) {
        this.mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("====动态代理====");
        //这就造成了死循环，拿代理对象掉代理方法  实际就成了 拿接口对象，掉接口，没有实现
//        Object invoke = method.invoke(proxy, args);
        //执行代理对象的对应方法，mObject就是实现业务的类  就等同于 avTeacher.buy();
        Object invoke = method.invoke(mObject, args);
        System.out.println("====结束====");
        return invoke;
    }
};

interface Subject {
    void buy();
}

class AvTeacher implements Subject {

    @Override
    public void buy() {
        System.out.println("Av老师帮忙卖了衣服");
    }
}

class Proxy implements Subject {
    private AvTeacher proxy;

    Proxy(AvTeacher proxy) {
        this.proxy = proxy;
    }

    @Override
    public void buy() {
        System.out.println("=====静态代理=====");
        proxy.buy();
        payMoney();
        System.out.println("=====结束====");
    }

    private void payMoney() {
        System.out.println("付款");
    }

}
