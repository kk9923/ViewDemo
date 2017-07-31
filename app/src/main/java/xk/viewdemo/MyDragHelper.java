package xk.viewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 31716 on 2017/7/31.
 */

public class MyDragHelper extends LinearLayout {
    private ViewDragHelper mDragger;
    private View firstView;

    public MyDragHelper(Context context) {
        this(context,null);
    }

    public MyDragHelper(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyDragHelper(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragger =  ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child==firstView;
            }

            /**
             *
             * @param child
             * @param left          当前view距离左屏幕的距离
             * @param dx            手指移动距离
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - child.getWidth() - leftBound;

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

                if (left < 0 ){
                    left = 0;
                }else if (left> getMeasuredWidth() - child.getMeasuredWidth()){
                    left = getMeasuredWidth() - child.getMeasuredWidth();
                }

                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top < 0 ){
                    top = 0;
                }else if (top> getMeasuredHeight() - child.getMeasuredHeight()){
                    top = getMeasuredHeight() - child.getMeasuredHeight();
                }
                return top;
            }
        });
       // mDragger.setEdgeTrackingEnabled();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        firstView = getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }
}
