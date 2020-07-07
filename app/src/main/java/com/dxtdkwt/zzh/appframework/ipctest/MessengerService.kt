package com.dxtdkwt.zzh.appframework.ipctest

import android.app.Service
import android.content.Intent
import android.os.*
import com.dxtdkwt.zzh.baselibrary.utils.LogUtils
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 通过Messenger 来跨平台传递数据
 */
class MessengerService : Service() {

//    companion object {
//        const val TAG = "MessengerService"
//
//        class MessengerHandler : Handler() {
//            override fun handleMessage(msg: Message) {
//                when (msg.what) {
//                    1 -> {
//                        LogUtils.i(TAG, "receive msg from CLient:" + msg.data.getString("msg"))
//                        val client = msg.replyTo
//                        val relayMessage = Message.obtain(null, 2)
//                        Bundle().apply {
//                            putString("reply","消息已经收到，稍后会回复你")
//                        }.also { relayMessage.data = it }
//                        client.send(relayMessage)
//                    }
//                    else -> super.handleMessage(msg)
//                }
//            }
//        }
//    }
//
//    val mBookList = CopyOnWriteArrayList<Book>();
//
//    val mBinder = object : IBookManager.Stub() {
//        override fun getBookList(): MutableList<Book> {
//            return mBookList
//        }
//
//        override fun addBook(book: Book?) {
//            mBookList.add(book)
//        }
//    }

//    val mMessenger = Messenger(MessengerHandler())

    override fun onBind(intent: Intent): IBinder {
//        return mMessenger.binder
        return Binder()
    }


}
