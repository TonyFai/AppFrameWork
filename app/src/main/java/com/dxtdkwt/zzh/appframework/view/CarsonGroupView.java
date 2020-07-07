package com.dxtdkwt.zzh.appframework.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import com.dxtdkwt.zzh.baselibrary.utils.LogUtils;

/**
 * 自定义ViewGroup:则需要重写 onLayout() 和 onMeasure()
 */
public class CarsonGroupView extends ViewGroup {

    /**
     * //缩紧的尺寸
     */
    private static final int OFFSET = 100;
    private int mLastXIntercept;
    private int mLastYIntercept;

    public CarsonGroupView(Context context) {
        super(context);
    }

    public CarsonGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarsonGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarsonGroupView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 根据规则确定子View位置
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //1.遍历子View
        //2.确定自己的规则
        //3.子View的测量尺寸
        //4.left,top,right,bottom
        //5.child.layout
//        int left = 0;
//        int top = 0;
//        int right = 0;
//        int bottom = 0;
//
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            left = i * OFFSET ;
//            right = left + child.getMeasuredWidth();
//            bottom = top + child.getMeasuredHeight();
//            child.layout(left,top,right,bottom);
//            top += child.getMeasuredHeight();
//            //不考虑padding margin 等其他属性
//        }
        //获取自身的padding大小
        final int paddingLeft = getPaddingLeft();
        int childTop = 0;
        int childLeft = 0;

        // Where right end of child should go
        final int width = right - left;
        int childRight = width - getPaddingRight();

        // Space available for child
        int childSpace = width - paddingLeft - getPaddingRight();

        final int count = getChildCount();

        final int majorGravity = Gravity.START | Gravity.TOP & Gravity.VERTICAL_GRAVITY_MASK;

        switch (majorGravity) {
            case Gravity.BOTTOM:
                // mTotalLength contains the padding already
                childTop = getPaddingTop() + bottom - top - 0;
                break;

            // mTotalLength contains the padding already
            case Gravity.CENTER_VERTICAL:
                childTop = getPaddingTop() + (bottom - top - 0) / 2;
                break;

            case Gravity.TOP:
            default:
                childTop = getPaddingTop();
                break;
        }

        LogUtils.i("渣渣辉", "子控件的数量为：" + count);
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child == null) {
                LogUtils.i("渣渣辉", "不为空的");
                childTop += 0;
            } else if (child.getVisibility() != GONE) {
                LogUtils.i("渣渣辉", "没有隐藏");
//                final int childWidth = child.getMeasuredWidth();
//                final int childHeight = child.getMeasuredHeight();

                childLeft = i * OFFSET;
                int childWidth = childLeft + child.getMeasuredWidth();
                int childHeight = childTop + child.getMeasuredHeight();

                child.layout(childLeft, childTop, childWidth, childHeight);
                childTop += child.getMeasuredHeight();

            }
        }
    }

    /**
     * 可能会进行多次测量
     * <p>
     * ViewGoup开始测量自己的尺寸
     * 为每个子View计算测量的限制信息
     * 把上一步确定的限制信息，传递给每一个子View，然子View开始measure自己的尺寸
     * 获取子View测量完成后的尺寸
     * ViewGroup根据自身的情况，计算自己的尺寸
     * 保存自身的尺寸
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //1.系统为什么要有measure过程？
        //在绘制的时候大小不确定   例如在xml布局中编写的wrap_content,match_parent
        //2.measure过程都干了点什么事？
        //自适应尺寸定义大小，获取到准确的大小，方便后面的绘制
        //3.对于自适应的尺寸机制，如何合理的测量一颗View树？
        //wrap_content 需要自View自己去计算值  等于自身的长度且等于小于父类布局的长度
        //MeasureSpec(测量规格，32位的int值) = Mode(测量模式，高2位即31，32位) + size(具体测量大小，低30位)
        //View自身LayoutParams    MeasureSpec   View最终的宽/高
        //父容器的MeasureSpec(自身的窗口尺寸DecorView)
        //4.那么ViewGroup是如何向子View传递限制信息的？
        //
        //5.ScrollView嵌套ListView问题？
        //MeasureSpec  是香把两种数据  合二为一  大小和模式两个数据   解决内存问题

        //1.测量自身
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //2.为每个子View计算测量的限制信息 Mode / Size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //3.把上一步确定的限制信息，传递给每一个子View，然子View开始measure自己的尺寸
        //子View的个数
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            //进行测量
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
            //测量完成后进行绘制
            child.measure(childWidthSpec, childHeightSpec);
        }
        //4.获取子View测量完成后的尺寸
        //5.ViewGroup根据自身的情况，计算自己的尺寸
        int width = 0;
        int height = 0;
        switch (widthMode) {
            //精确大小
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;

            case MeasureSpec.UNSPECIFIED:
                //父View有上线没下限
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    int widthAddOffset = i * OFFSET + child.getMeasuredWidth();
                    width = Math.max(width, widthAddOffset);//获取最大宽度
                }
                break;
            default:
                break;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                //父View有上线没下限
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    height += child.getMeasuredHeight();
                }
                break;
            default:
                break;
        }

        //6.保存自身的尺寸
        setMeasuredDimension(width, height);
    }

    //常用的方法onMeasure();

    /**
     * getChildCount();获取子View的数量
     * getChildAt(i) 获取第i个控件
     * subView.getLayoutParams().width/height 设置或获取子控件的宽或高
     * measureChild(child,widthMeasureSpec,heightMeasureSpec);测量子View的宽高
     * child.getMeasuredHeight/width() 执行完measureChild()方法后就可以通过这种方式获取子View的宽高值
     * getPaddingLeft/Right/Top/Bottom()获取控件的四周内边距
     * setMeasureDimension(width,height);重新设置控件的宽高
     */


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            //不可拦截   不受FLAG_DISALLOW_INTERCEPT 这个标记位的控制，一旦拦截，所有订单事件都无法传递到子元素中去
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (onTouchEvent(ev)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
