// IBookManagers.aidl
package com.dxtdkwt.zzh.appframework.ipctest;

// Declare any non-default types here with import statements

import com.dxtdkwt.zzh.appframework.ipctest.Book;
import com.dxtdkwt.zzh.appframework.ipctest.IOnNewBookArrivedListener;

interface IBookManagers {
    List<Book> getBookList();
    void addBook(in Book book);

//通过下边两个方法  建立观察者模式  实时将新数据返回给客户端
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
