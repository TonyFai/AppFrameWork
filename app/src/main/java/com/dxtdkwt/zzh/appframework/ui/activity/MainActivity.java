package com.dxtdkwt.zzh.appframework.ui.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dxtdkwt.zzh.appframework.BuildConfig;
import com.dxtdkwt.zzh.appframework.DesktopPendant.DesktopPendantActivity;
import com.dxtdkwt.zzh.appframework.R;
import com.dxtdkwt.zzh.appframework.jni.JinTest;
import com.dxtdkwt.zzh.appframework.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public native String sayHello(String string);

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        final TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
//        JinTest jinTest = new JinTest();
        String str = sayHello("你好你好");
        System.out.println("从jni返回的数据为："+str);

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
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        FragmentTransaction add = fragmentTransaction.add(R.id.wb_view, MainFragment.newInstance("123", "231"));
//        fragmentTransaction.commit();

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
                .setNegativeButton("跳转", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, DesktopPendantActivity.class));
                        Toast.makeText(MainActivity.this, "跳转成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
//        Notificaitons.getInstance().sendBigPictureStyleNotification(MainActivity.this, nm);
        tv.setText("从jni返回的数据为："+str);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
