package com.dxtdkwt.zzh.appframework.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dxtdkwt.zzh.appframework.R;
import com.dxtdkwt.zzh.baselibrary.utils.LogUtils;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);


        View view = new View(this);
        ObjectAnimator.ofFloat(view, "translationY", -view.getHeight()).start();

        ValueAnimator colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", 0xffff8080, 0xff8080ff);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(view, "rotationX", 0, 180),
                ObjectAnimator.ofFloat(view, "rotation", 0, -90),
                ObjectAnimator.ofFloat(view, "translationX", 0, 90),
                ObjectAnimator.ofFloat(view, "translationY", 0, 90),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 1.5f),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 0.5f),
                ObjectAnimator.ofFloat(view, "alpha", 1, 0.25f, 1)
        );
        set.setDuration(5 * 1000).start();

    }

    private void performAnimate(View view) {
        ViewWrapper wrapper = new ViewWrapper(view);
        ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(5000).start();
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

    private void performAnimate(View target, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获得当前动画的进度值，整型，1～100之间
                int currentValue = (Integer) animation.getAnimatedValue();
                LogUtils.i("current value: " + currentValue);
                //获得当前进度占整个动画过程的比例，浮点型，0～1之间
                float fraction = animation.getAnimatedFraction();
                //直接调用整型估值器，通过比例计算出宽度，然后在设给View
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }
}