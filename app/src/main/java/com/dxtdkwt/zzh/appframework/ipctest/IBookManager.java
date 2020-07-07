package com.dxtdkwt.zzh.appframework.ipctest;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ipc.IIPCService;

import java.util.List;

public interface IBookManager extends android.os.IInterface {
    //唯一表示
    static final String DESCRIPTOR = "com.dxtdkwt.zzh.appframework";

    int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;

    int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;


    public class Stub extends Binder implements IBookManager{

        private Stub mBookManager;

        public Stub(){
            this.attachInterface(this,DESCRIPTOR);
        }

        public static IBookManager asInterface(IBinder obj){
            if (obj == null){
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IBookManager))){
                return (IBookManager)iin;
            }
            return new Stub.Proxy(obj);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {

            return null;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            switch (code){
                case INTERFACE_TRANSACTION:{
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getBookList:
                    data.enforceInterface(DESCRIPTOR);
                    List<Book> bookList = this.getBookList();
                    reply.writeNoException();
                    reply.writeTypedList(bookList);
                    return true;
                case TRANSACTION_addBook:{
                    data.enforceInterface(DESCRIPTOR);
                    Book arg0;
                    if (0 != data.readInt()){
                        arg0 = Book.CREATOR.createFromParcel(data);
                    }else{
                        arg0 = null;
                    }
                    this.addBook(arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        private static class Proxy implements IBookManager{
            private IBinder mRemote;
            Proxy(IBinder remote){
                mRemote = remote;
            }

            public String getInterfaceDescriptor(){
                return DESCRIPTOR;
            }

            @Override
            public List<Book> getBookList() throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                List<Book> result;
                try{
                    data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(TRANSACTION_getBookList,data,reply,0);
                    reply.readException();
                    result = reply.createTypedArrayList(Book.CREATOR);
                }finally {

                    reply.recycle();
                    data.recycle();
                }
                return result;
            }

            @Override
            public void addBook(Book book) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try{
                    data.writeInterfaceToken(DESCRIPTOR);
                    if (book != null){
                        data.writeInt(1);
                        book.writeToParcel(data,0);
                    }else{
                        data.writeInt(0);
                    }
                    mRemote.transact(TRANSACTION_addBook,data,reply,0);
                    reply.readException();
                }finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }
        }
        protected IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
            @Override
            public void binderDied() {
                if (mBookManager == null)
                {
                    return;
                }
                mBookManager.asBinder().unlinkToDeath(mDeathRecipient,0);
                mBookManager = null;



                //todo 这里重新绑定远程service
            }
        };
    }



}
