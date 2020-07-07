package com.dxtdkwt.zzh.appframework.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import androidx.annotation.NonNull;

import com.nineoldandroids.view.ViewHelper;

public class TestButton extends androidx.appcompat.widget.AppCompatTextView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetector mGestureDetector;

    Scroller mScroller = new Scroller(getContext());
    private int mLastX;
    private int mLastY;
    private String TAG;


    public TestButton(Context context) {
        super(context);
    }

    public TestButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    //scrollTo/scrollBy   缺点  只能滑动view的内容，并不能滑动view本身  简单
    //动画滑动  缺点  3.0以下,点击事件不会作用与移动的残影上  适用于没有交互的View和实现复杂的动画效果
    //改变布局  缺点  使用起来麻烦

    //缓慢滚动到制定位置
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1000ms内滑向destX,效果就是慢慢滑动
        mScroller.startScroll(scrollX, 0, delta, 0, 1000);
        //invalidate 会导致View重绘，在View的draw方法中又会去调用computeScroll方法，computeScroll方法在View中是一个空实现，

        invalidate();
    }

    //当View重绘后会在draw方法中调用computeScroll，
    // 而computeScroll又回去向Scroller获取当前的scrollX和scrollY，
    // 然后通过scrollTo方法实现滑动，
    // 接着又调用postInvalidate方法来进行第二次重绘，
    // 这一次重绘的过程和第一次重绘一样，
    // 还是会导致computeScroll方法被调用，
    // 然后继续向scroller获取当前的scrollX和scrollY，
    // 并通过scrollTo方法滑动到新的位置，
    // 如此反复，
    // 直到整个滑动过程结算
    //弹性滑动
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }

        //通过动画 弹性滑动效果
        ObjectAnimator.ofFloat(this,
                "translationX", 0, 100)
                .setDuration(100).start();

        final int startX = 0;
        final int deltaX = 100;
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                TestButton.this.scrollTo(startX + (int) (deltaX * fraction), 0);
            }
        });
        animator.start();
    }

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private int mCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);
                        TestButton.this.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
                //滑动
                int translationX = (int) (ViewHelper.getTranslationX(this) + deltaX);
                int translationY = (int) ViewHelper.getTranslationY(this) + deltaY;
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;

        }
        mLastX = x;
        mLastY = y;

        //最小滑动距离
        int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        //速度追踪
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);

        velocityTracker.computeCurrentVelocity(1000);
        //x的滑动速度  速度= （终点位置 - 起点位置） / 时间段
        float xVelocity = velocityTracker.getXVelocity();
        //y的滑动速度
        float yVelocity = velocityTracker.getYVelocity();

        //回收内存
        velocityTracker.clear();
        velocityTracker.recycle();


        //手势检测
        mGestureDetector = new GestureDetector(this);
        //解决长按屏幕后无法拖动的现象
        mGestureDetector.setIsLongpressEnabled(false);

        //通过手势检测  接管view的onTouchEvent
        boolean consume = mGestureDetector.onTouchEvent(event);

        return consume;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (onTouchEvent(event)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;

        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    /**
     * 手指轻轻触摸屏幕的一瞬间，由一个ACTION_DOWN触发
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    /**
     * 手指轻轻触摸屏幕，尚未松开或拖动，由一个ACTION_DOWN触发
     * 注意和onDown的区别，它强调的是没有松开或者拖动的状态
     *
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * 手指（轻轻触摸屏幕后）松开，伴随着一个MotionEvent ACTION_UP而触发，这是单击行为
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * 手指按下屏幕开拖动，由一个ACTION——DOWN，多个ACTION_MOVE触发，这是拖动行为
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /**
     * 用户长久拖按着屏幕不放，即长按
     *
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 用户按下触摸屏/快速滑动后松开，由一个ACTION_DEOWN，多个ACTION_MOVE和一个ACTION_UP触发，这是快速滑动行为
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    //========================================

    /**
     * 严格的单击行为
     * 注意它和onSingleTagUp的区别，如果触发了onSingleTapConfirmed，那么后面不可能再紧跟着另一个单击行为，即这只可能是单击，而不可能是双击中的一次单击
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /**
     * 双击，由2次连续的单击组成，它不可能和onSingleTapConfirmed共存
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    /**
     * 表示发生了双击行为，在双击的期间，ACTION_DOWN、ACTION_MOVE和ACTION_UP都会触发此回调
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

//    private static final int MODE_SHIFT = 30;
//    private static final int MODE_MASK = 0x3 << MODE_SHIFT;
//    public static final int UNSPECIFIED = 0 << MODE_SHIFT;
//    public static final int EXACTLY = 1 << MODE_SHIFT;
//    public static final int AT_MOST = 2 << MODE_SHIFT;
//
//    public static int makeMeasureSpec(int size, int mode) {
//        if (sUseBrokenMakeMeasureSpec) {
//            return size + mode;
//        } else {
//            return (size & ~MODE_MASK) | (mode & MODE_MASK);
//        }
//    }
//
//    public static int getMode(int measureSpec) {
//        return (measureSpec & MODE_MASK);
//    }
//
//    public static int getSize(int measureSpec) {
//        return (measureSpec & ~MODE_MASK);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}





























