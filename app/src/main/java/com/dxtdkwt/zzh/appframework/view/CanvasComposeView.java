package com.dxtdkwt.zzh.appframework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasComposeView extends View {

    public CanvasComposeView(Context context) {
        super(context);
    }

    public CanvasComposeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasComposeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //构造两个画笔，一个红色，一个绿色
        Paint paint_green = generatePaint(Color.GREEN, Paint.Style.STROKE, 10);
        Paint paint_red = generatePaint(Color.RED, Paint.Style.STROKE, 10);

        //构造一个矩形
        Rect rect1 = new Rect(0, 0, 400, 220);

        //在平移画布前用绿色画下边框
        canvas.drawRect(rect1, paint_green);

        //平移画布后,再用红色边框重新画下这个矩形
        canvas.translate(100, 100);
        canvas.drawRect(rect1, paint_red);
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }
}
