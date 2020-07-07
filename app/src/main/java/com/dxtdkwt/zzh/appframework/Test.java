package com.dxtdkwt.zzh.appframework;

import android.os.Handler;

import com.dxtdkwt.zzh.appframework.ipctest.Book;
import com.dxtdkwt.zzh.baselibrary.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.Date;
import java.util.TreeSet;

public class Test {

    TreeSet<Book> mBooks;

    public static void main(String[] args) {
//        boolean json = "json".equals(new String("json"));
        boolean json = "json" == "json";
        System.out.println(json);
        String[] strings = {"dce33d97cc134a73a7c506d8b3ae9a04", "小会", "69c515bf57184d70bac8534147d7c110", "13300000000", "4203cc28e11b4af78007319349d91071", "", "7b6aaf34a1fe45a5b9b7d8b743fe578f", "", "902c2272bc794af6b31a705a1b768328", "男"};
        Gson gson = new Gson();
        String x = gson.toJson(strings);
        System.out.println("转后的数据为："+x);


        int a = 0;
        while (a<5){
            switch (a){
                case 0:
                case 3:a = a+2;
                case 2:a = a +3;
                default: a = a+5;
            }
        }
        System.out.println(a);
    }

    public void callStaticMethod(String str, int i) {
        ToastUtils.showShort( "数据为：" + str + "  " + i);
    }

    /**
     * jni  核心通过反射获取java方法，或者修改对应的值等
     */
    public native void callJavaInstanceMethod();

    public native String getCurrentTime();
}
