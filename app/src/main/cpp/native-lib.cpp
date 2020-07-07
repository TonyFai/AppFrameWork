#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <string>
#include "android/log.h"
//#include <Windows.h>


extern "C" JNIEXPORT
//jstring 返回值类型   JNIEXPOERT  和 JNICALL  代表 jni接口
jstring JNICALL
// 该为静态接口  规则为  Java_包名_类名_方法名
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

//案例签名：pubilc intfun(int arg1,String arg2,long[] arg3);
//(ILjava/lang/String;[J)I
extern "C" JNIEXPORT jstring JNICALL
Java_com_dxtdkwt_zzh_appframework_jni_JinTest_sayHello(JNIEnv *env, jobject thiz, jstring jstr) {
    // TODO: implement sayHello()
    const char *c_str = NULL;
    char buf[128] = {0};
    jboolean iscopy;
    c_str = (env)->GetStringUTFChars(jstr, &iscopy);
    printf("isCopy:%d\n", iscopy);
    if (c_str == NULL) {
        return NULL;
    }
    printf("C_str: %s \n", c_str);
    sprintf(buf, "Hello, 你好 %s", c_str);
    printf("C_str: %s \n", buf);
    (env)->ReleaseStringUTFChars(jstr, c_str);
    return (env)->NewStringUTF(buf);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_sayHello(JNIEnv *env, jobject thiz,
                                                                    jstring jstr) {
    // TODO: implement sayHello()
    const char *c_str = NULL;
    char buf[128] = {0};
    jboolean iscopy;
    c_str = (env)->GetStringUTFChars(jstr, &iscopy);
    printf("isCopy:%d\n", iscopy);
    if (c_str == NULL) {
        return NULL;
    }
    printf("C_str: %s \n", c_str);
    sprintf(buf, "Hello, 你好 %s", c_str);
    printf("C_str: %s \n", buf);
    (env)->ReleaseStringUTFChars(jstr, c_str);
    return (env)->NewStringUTF(buf);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_getNativeString(JNIEnv *env,
                                                                           jobject job) {
    jstring string = (env)->NewStringUTF("Hello Word");
    return string;
}

extern "C" JNIEXPORT void JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_testField(JNIEnv *env, jobject job) {
    //获取java的类
    jclass claz = env->GetObjectClass(job);
    //根据java类拿到对应的变量property 签名为 I
    jfieldID jfid = env->GetFieldID(claz, "property", "I");
    //拿到变量后  在获取里面的值
    jint va = env->GetIntField(job, jfid);
    //进行重新设置
    env->SetIntField(job, jfid, va + 10086);
}

extern "C" JNIEXPORT void JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_test(JNIEnv *env, jobject job) {
    //获取java的类
    jclass hello_clz = env->GetObjectClass(job);
    //根据java类拿到对应的变量property 签名为 I
    jfieldID fieldId_prop = env->GetFieldID(hello_clz, "property", "I");
    //拿到变量后  在获取里面的值   (ILjava/util/Date;[I)I 签名  function 方法名
    //int function(int a,Date data,int[]arr)
    jmethodID methodId_func = env->GetMethodID(hello_clz, "function", "(ILjava/util/Date;[I)I");
    //进行重新设置
    env->CallIntMethod(job, methodId_func, 0L, NULL, NULL);
}



extern "C" JNIEXPORT jstring JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_sayChineseHello(JNIEnv *env,
                                                                           jclass jclaz,
                                                                           jstring jstr) {
    jboolean iscp;
    // 将str参数 转化为char指针
    char *c_str = (char *) env->GetStringChars(jstr, &iscp);
    if (iscp == NULL) {
        printf("is copy: JNI_TRUE\n");
    } else if (iscp == JNI_FALSE) {
        printf("is copy: JNI_FALSE\n");
    }
    //获取 入参的长度值
    int length = env->GetStringLength(jstr);

    const jchar *jchar = env->GetStringChars(jstr, NULL);
    //jchar->char  请求多大的空间大小
    char *rtn = (char *) (malloc(sizeof(char) * 2 * length + 3));
    memset(rtn, 0, sizeof(char) * 2 * length + 3);
    int size = 0;
//    size = Widec

}

extern "C" JNIEXPORT void JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_testStatic
        (JNIEnv *env, jclass jobject1) {
    jclass clz = jobject1;
    //public static int test(int i)
    jmethodID jmethodId = (env)->GetStaticMethodID(clz, "test", "(I)I");
    (env)->CallStaticIntMethod(clz, jmethodId, 0L);

}

extern "C" JNIEXPORT void JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_callJavaStaticMethod(JNIEnv *env,
                                                                                jobject job) {
    jclass clz = env->FindClass(
            "com.dxtdkwt.zzh.appframework.ui.activity.MainActivity.ClassMethod");
    if (clz == NULL) {
        printf("clz is null");
        return;
    }
    //public static void callStaticMethod(String str,int i)
    jmethodID jmeid = env->GetStaticMethodID(clz, "callStaticMethod", "(Ljava/lang/String;I)V");
    if (jmeid == NULL) {
        printf("jmeid is null");
        return;
    }
    jstring arg = env->NewStringUTF("I am from JNI");
    env->CallShortMethod(clz, jmeid, arg, 100);
    env->DeleteGlobalRef(clz);
    env->DeleteGlobalRef(arg);
}

extern "C" JNIEXPORT void JNICALL
Java_com_dxtdkwt_zzh_appframework_Test_callJavaInstanceMethod(JNIEnv *env,
                                                              jobject job) {
    jclass clz = env->FindClass("com/dxtdkwt/zzh/appframework/Test");
    if (clz == NULL) {
        printf("clz is null");
        return;
    }
    jmethodID jconstanceId = env->GetMethodID(clz, "<init>", "()V");
    if (jconstanceId == NULL) {
        printf("jconstanceId is null");
        return;
    }
    jobject jobj = env->NewObject(clz, jconstanceId);
    //public static void callStaticMethod(String str,int i)
    jmethodID jmeid = env->GetMethodID(clz, "callStaticMethod", "(Ljava/lang/String;I)V");
    if (jmeid == NULL) {
        printf("jmeid is null");
        return;
    }
    jstring arg = env->NewStringUTF("I am from JNI");
    env->CallVoidMethod(jobj, jmeid, arg, 100);
    env->DeleteLocalRef(clz);
    env->DeleteLocalRef(jobj);
    env->DeleteLocalRef(arg);

}

extern "C" JNIEXPORT jstring JNICALL Java_Refrence_newString(JNIEnv *env, jobject jobj, jint len) {
    jcharArray elemArray;
    jchar *chars = NULL;
    jstring j_str = NULL;
    // ∂®“Âæ≤Ã¨µƒæ÷≤ø±‰¡ø
    static jclass cls_string = NULL;
    static jmethodID jmetid = NULL;

    if (cls_string == NULL) {
        printf("cls_string is null \n");
        cls_string = (env)->FindClass("java/lang/String");
        if (cls_string == NULL) {
            return NULL;
        }
    }

    if (jmetid == NULL) {
        printf("jmetid is null \n");
        jmetid = (env)->GetMethodID(cls_string, "<init>", "([C)V");
        if (jmetid == NULL) {
            return NULL;
        }
    }
    printf("this is a line -------------\n");

    elemArray = (env)->NewCharArray(len);
    printf("this is a line2 -------------\n");
    printf("this is a %d,%d,%d\n", cls_string, jmetid, elemArray);

    j_str = (jstring) (env)->NewObject(cls_string, jmetid, elemArray);
    printf("this is a line3 -------------\n");
    (env)->DeleteLocalRef(elemArray);
    char *ch = (char *) (env)->GetStringUTFChars(j_str, NULL);

    elemArray = NULL;
//
    (env)->DeleteLocalRef(cls_string);
    cls_string = NULL;
//    // ¥À¥¶µƒ delete≤ªƒ‹¥Ê‘⁄£¨“ÚŒ™ jmetid≤ª «jobject£¨”¶”√÷ª–Ë“™∂‘object¿‡–Õµƒ“˝”√∂¯—‘µƒ£¨
//     (*env)->DeleteLocalRef(env, jmetid);
    jmetid = NULL;
    printf("end of function \n");
    return j_str;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_dxtdkwt_zzh_appframework_Test_getCurrentTime(JNIEnv *env, jobject obj) {
    jclass dateclz = (env)->FindClass("java/util/Date");
    if (dateclz == NULL) {
        printf("dateclz getclass null");
        return NULL;
    }
    jmethodID method_id = (env)->GetMethodID(dateclz, "<init>", "()V");
    if (method_id == NULL) {
        printf("method_id GetMethodID null");
        return NULL;
    }
    jobject date = (env)->NewObject(dateclz, method_id);
    jmethodID getTime = (env)->GetMethodID(dateclz, "getTime", "()J");
    jlong time = ((env)->CallLongMethod(date, getTime));

    jclass strObj = env->FindClass("java/lang/String");
    jmethodID id = env->GetStaticMethodID(strObj, "valueOf", "(J)Ljava/lang/String;");
    jstring str = (jstring) (env->CallStaticObjectMethod(strObj, id, time));

    printf("time form java: %lld\n", time);
    const char * tag = "Logg";
    const char *log = "日志";
    __android_log_write(ANDROID_LOG_INFO,tag,log);
    return str;
}