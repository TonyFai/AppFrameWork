package com.dxtdkwt.zzh.appframework.ipctest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.airbnb.lottie.L;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private boolean mIsServiceDestoryed = false;
    private String[] mDefinedMessages = new String[]{
            "你好啊，哈哈",
            "请问你叫什么名字呀？",
            "今天北京天气不错啊，sky",
            "你知道吗？我可是可以和多个人同时聊天的哦",
            "给我讲个笑话吧：据说爱笑的人运气不会太差，不知道真假."
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TCPServerService","页面开启了");
        new Thread(new TcpServer()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestoryed = true;
    }

    private class TcpServer implements Runnable{
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try{
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.out.println("establish tcp server failed,port : 8868");
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestoryed){
                try {
                    Socket client = serverSocket.accept();
                    System.out.println("accept");

                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
        out.println("欢迎来到聊天室！");
        while (!mIsServiceDestoryed){
            String str = in.readLine();
            System.out.println("msg from client :"+ str);
            if (str == null){
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("send:"+msg);
        }
        System.out.println("client quit.");
        if (in != null){
            in.close();
        }
        if (out != null){
            out.flush();
            out.close();
        }
        client.close();
    }
}
