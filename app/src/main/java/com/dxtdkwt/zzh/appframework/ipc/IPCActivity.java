package com.dxtdkwt.zzh.appframework.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dxtdkwt.zzh.appframework.R;
import com.dxtdkwt.zzh.utilslibrary.ext.ToastUtils;
import com.example.ipc.IPC;
import com.example.ipc.IPCService;

public class IPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, GpsService.class));

        IPC.bind(this, IPCService.IPCService0.class);
        TextView tv = findViewById(R.id.sample_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ILocationManager getInstance = IPC.getInstance(IPCService.IPCService0.class,"getDefault", ILocationManager.class );
                ToastUtils.show(IPCActivity.this, "获取的地址为：" + getInstance.getLocation().toString());
            }
        });
    }
}
