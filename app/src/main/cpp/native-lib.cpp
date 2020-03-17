#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <string>


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
    c_str = (env)->GetStringUTFChars( jstr, &iscopy);
    printf("isCopy:%d\n", iscopy);
    if (c_str == NULL) {
        return NULL;
    }
    printf("C_str: %s \n", c_str);
    sprintf(buf, "Hello, 你好 %s", c_str);
    printf("C_str: %s \n", buf);
    (env)->ReleaseStringUTFChars( jstr, c_str);
    return (env)->NewStringUTF( buf);
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_dxtdkwt_zzh_appframework_ui_activity_MainActivity_sayHello(JNIEnv *env, jobject thiz,
                                                                    jstring jstr) {
    // TODO: implement sayHello()
    const char *c_str = NULL;
    char buf[128] = {0};
    jboolean iscopy;
    c_str = (env)->GetStringUTFChars( jstr, &iscopy);
    printf("isCopy:%d\n", iscopy);
    if (c_str == NULL) {
        return NULL;
    }
    printf("C_str: %s \n", c_str);
    sprintf(buf, "Hello, 你好 %s", c_str);
    printf("C_str: %s \n", buf);
    (env)->ReleaseStringUTFChars( jstr, c_str);
    return (env)->NewStringUTF( buf);
}