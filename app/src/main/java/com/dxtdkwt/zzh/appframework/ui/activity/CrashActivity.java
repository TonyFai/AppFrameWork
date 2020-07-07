package com.dxtdkwt.zzh.appframework.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dxtdkwt.zzh.appframework.R;

public class CrashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        initView();
    }

    private void initView() {
        mButton = findViewById(R.id.button1);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton){
            //在这里模拟异常抛出情况，人为地抛出一个运行时异常
            throw new RuntimeException("自定义异常：这里自己抛出到异常");
        }
    }
}