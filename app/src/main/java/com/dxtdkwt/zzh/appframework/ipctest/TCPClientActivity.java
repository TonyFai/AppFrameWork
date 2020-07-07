package com.dxtdkwt.zzh.appframework.ipctest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dxtdkwt.zzh.appframework.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TCPClientActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TcpClient";

    private static final int MESSAGE_SOCKET_CONNECTED = 1;
    private static final int MESSAGE_SOCKET_NEW_MSG = 2;
    private PrintWriter mPrintWriter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_SOCKET_CONNECTED: {
                    Log.d(TAG, "可点击");
                    mSendButton.setEnabled(true);
                    break;
                }
                case MESSAGE_SOCKET_NEW_MSG: {
                    Log.d(TAG, "新的数据是：" + msg.obj);
                    mMessageEditText.setText(mMessageTextView.getText().toString() + msg.obj);
                    break;
                }
            }
        }
    };
    private Socket mClientSocker;
    private TextView mMessageTextView;
    private Button mSendButton;
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_c_pclient);

        mMessageTextView = findViewById(R.id.msg_container);
        mSendButton = findViewById(R.id.send);
        mSendButton.setOnClickListener(this);
        mMessageEditText = findViewById(R.id.msg);

        Intent service = new Intent(this, TCPServerService.class);
        startService(service);


        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocker = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success.");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                System.out.println("connect tcp server failed,retry...");
            }
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("receive:" + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server " + time +
                            ": " + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_SOCKET_NEW_MSG, showedMsg).sendToTarget();
                }
            }
            System.out.println("quit..");
            if (mPrintWriter != null) {
                mPrintWriter.close();
            }
            if (br != null) {
                br.close();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        if (mClientSocker != null) {
            try {
                mClientSocker.shutdownInput();
                mClientSocker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == mSendButton) {
            String msg = mMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                mPrintWriter.println(msg);
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self " + time + " :" + msg + "\n";
                mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
            }

        }
    }

    private String formatDateTime(long currentTimeMillis) {
        return new SimpleDateFormat("(HH:mm:ss)", Locale.getDefault()).format(new Date((currentTimeMillis)));
    }
}