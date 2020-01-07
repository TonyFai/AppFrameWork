package com.dxtdkwt.zzh.appframework;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        final TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        new Thread() {
            @Override
            public void run() {
                super.run();
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                tv.setText("窝窝头，一块钱四个");
            }
        }.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tv.setText("嘿嘿");
            }
        };


        new Thread(runnable).start();

        final NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        tv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
//                new AlertDialog.Builder(MainActivity.this).setTitle("哈哈")
//                        .show();

//                sendBigPictureStyleNotification(MainActivity.this,nm);\

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                //子线程
                                Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();
//                                tv.setText("啦啦啦");
                            }
                        };
                        handler.sendEmptyMessage(1);
                        Looper.loop();
                    }
                }).start();
            }
        });

//        coil 图片加载

        new AlertDialog.Builder(MainActivity.this).setTitle("哈哈")
                .show();
//        Notificaitons.getInstance().sendBigPictureStyleNotification(MainActivity.this, nm);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
