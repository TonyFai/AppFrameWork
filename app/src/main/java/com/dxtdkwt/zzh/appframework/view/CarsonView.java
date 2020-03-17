package com.dxtdkwt.zzh.appframework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CarsonView extends View {

    private final Rect mRect = new Rect(0,0,200,200);
    private Paint mPaint;

    public CarsonView(Context context) {
        super(context);
        init();
    }

    public CarsonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CarsonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.translate(100,100);
        //绘制屏幕颜色
        canvas.drawColor(Color.GRAY);

        canvas.drawRect(mRect,mPaint);

        canvas.translate(0,300);
        //缩放的是画布   所以图形看起来变小了一倍
        canvas.scale(0.5f,0.5f);
        canvas.drawRect(mRect,mPaint);

        canvas.translate(0,300);
        //旋转了
        canvas.rotate(30);
        canvas.drawRect(mRect,mPaint);

        canvas.translate(0,300);
        //扭曲了
        canvas.skew(5f,5f);
        canvas.drawRect(mRect,mPaint);
    }

}
