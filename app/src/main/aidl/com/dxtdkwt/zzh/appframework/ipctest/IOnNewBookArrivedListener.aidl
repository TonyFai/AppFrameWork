// IOnNewBookArrivedListener.aidl
package com.dxtdkwt.zzh.appframework.ipctest;

import com.dxtdkwt.zzh.appframework.ipctest.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
