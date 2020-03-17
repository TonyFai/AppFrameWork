package com.dxtdkwt.zzh.appframework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BitmapDrawView extends View {
    public BitmapDrawView(Context context) {
        super(context);
    }

    public BitmapDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Path path = new Path();
        //沿逆时针方向绘制
        path.addOval(new RectF(), Path.Direction.CCW);
        //CW  沿顺时针方向绘制
    }
}
