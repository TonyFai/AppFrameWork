// ISecurityCenter.aidl
package com.dxtdkwt.zzh.appframework.ipctest;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
