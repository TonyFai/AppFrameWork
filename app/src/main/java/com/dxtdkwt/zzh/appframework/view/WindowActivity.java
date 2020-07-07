package com.dxtdkwt.zzh.appframework.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.dxtdkwt.zzh.appframework.R;
import com.dxtdkwt.zzh.baselibrary.utils.ToastUtils;
import com.google.android.material.snackbar.Snackbar;

public class WindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout);

        Button mFloatingButton = new Button(this);
        mFloatingButton.setText("button");
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSPARENT
        );
        //系统会将当前Window区域以外的单击事件传递给底层的Window，当前Window区域以内的单击事件则自己处理
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                //不需要获取焦点
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                //让window显示在锁屏的界面上
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT|Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        //指定层级
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        frameLayout.addView(mFloatingButton,mLayoutParams);



        //Palette 使用   提取颜色
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bitmapdrawable);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(swatch.getRgb()));
            }
        });

        Snackbar.make(frameLayout,"标题",Snackbar.LENGTH_LONG)
                .setAction("点击事件", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("Toast");
                    }
                }).setDuration(Snackbar.LENGTH_LONG).show();

    }
}