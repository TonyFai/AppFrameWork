package com.example.ipc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//运行时做处理
@Retention(RetentionPolicy.RUNTIME)
//在类上
@Target(ElementType.TYPE)
public @interface ServiceId {
    String value();
}
