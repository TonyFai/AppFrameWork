package com.dxtdkwt.zzh.appframework.ipctest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dxtdkwt.zzh.appframework.R
import java.io.*

class MessengerActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MessengerActivity"

        class MessengerHandler : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    2 -> Log.i(TAG, "receive msg from Service:" + msg.data.getString("reply"))
                    else -> super.handleMessage(msg)
                }
            }
        }
    }

    lateinit var mService: Messenger

    private val mGetReplyMessenger = Messenger(MessengerHandler())

    private val mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            val msg = Message.obtain(null, 1)
            Bundle().apply {
                putString("msg", "hello this is client.")
            }.also {
                msg.data = it
            }

            //重点
            msg.replyTo = mGetReplyMessenger
            mService.send(msg)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        Intent(this, MessengerService::class.java)
                .also {
                    bindService(it, mConnection, Context.BIND_AUTO_CREATE)
                }
    }

    override fun onDestroy() {
        unbindService(mConnection)
        super.onDestroy()
    }

    /**
     * 文件共享 来传递数据
     */
//    private fun persistToFile() {
//        Thread(Runnable {
//            val book = Book(1, "你好")
//            val dir = File(filesDir.absolutePath)
//            if (!dir.exists()) {
//                dir.mkdirs()
//            }
//            val cachedFile = File(filesDir.absolutePath)
//            var objectOutputStream: ObjectOutputStream? = null
//            try {
//                objectOutputStream = ObjectOutputStream(FileOutputStream(cachedFile))
//                objectOutputStream.writeObject(book)
//            } catch (e: IOException) {
//                e.printStackTrace()
//                if (objectOutputStream != null) {
//                    objectOutputStream.flush()
//                    objectOutputStream.close()
//                }
//            }
//        }).start()
//    }
//
//    private fun recoverFromFile() {
//        Thread(Runnable {
//            var book: Book? = null
//            val cachedFile = File(filesDir.absolutePath)
//            if (cachedFile.exists()) {
//                var objectInputStream: ObjectInputStream? = null
//                try {
//                    objectInputStream = ObjectInputStream(FileInputStream(cachedFile))
//                    book = objectInputStream.readObject() as Book
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                } catch (e: ClassNotFoundException) {
//                    e.printStackTrace()
//                } finally {
//                    if (objectInputStream != null) {
//                        objectInputStream.close()
//                    }
//                }
//            }
//        }).start()
//    }

    lateinit var mSecurityCenter: ISecurityCenter
    lateinit var mCompute: ICompute

    private fun doWork() {
        val binderPool = BinderPool.getInstance(this)
        val securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER)
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder)
        Log.d(TAG, "visit ISecurityCenter")
        val msg = "helloworld-安卓"
        System.out.println("content:" + msg)
        try {
            val password = mSecurityCenter.encrypt(msg)
            System.out.println("encrypt:" + password)
            System.out.println("decrypt:" + mSecurityCenter.decrypt(password))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.d(TAG, "visit ICompute")
        val computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE)
        mCompute = ComputeImpl.asInterface(computeBinder)
        try {
            System.out.println("3+5=" + mCompute.add(3, 5))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}
