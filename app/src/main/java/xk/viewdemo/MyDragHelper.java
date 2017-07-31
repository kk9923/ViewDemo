package xk.viewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
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
    private View firstView,secondView;

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
              //  return child==firstView;
                DrawerLayout drawerLayout = new DrawerLayout(getContext());
                return true;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return super.getViewHorizontalDragRange(child);
            }

            /**
             * @param child
             * @param left          当前view距离左屏幕的距离
             * @param dx            手指移动距离
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
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

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
               if (changedView == firstView ){
                   secondView.layout(secondView.getLeft()+dx,secondView.getTop()+dy,secondView.getRight()+dx,secondView.getBottom()+dy);
               }else {
                   firstView.layout(firstView.getLeft()+dx,firstView.getTop()+dy,firstView.getRight()+dx,firstView.getBottom()+dy);
               }
            }

            /**
             * 手指松开时的回调
             * @param releasedChild
             * @param xvel      //水平方向的速度   右 ： 正    左  ： 负
             * @param yvel       //竖直方向的速度  下 ： 正    上  ： 负
             */
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                int centerLeft = getMeasuredWidth() /2  - releasedChild.getMeasuredWidth()/2 ;
                if (releasedChild .getLeft() <centerLeft){     // 松手时中心在屏幕左边，执行动画，滚到屏幕左边指定位置
                    mDragger.smoothSlideViewTo(releasedChild,0,releasedChild.getTop());   //设置view的平滑滚动    参数   滚动的目标view ， 滚动停止时候的left，滚动停止时的top
                    ViewCompat.postInvalidateOnAnimation(MyDragHelper.this);           //  开启动画
                }else {
                    mDragger.smoothSlideViewTo(releasedChild,getMeasuredWidth() - releasedChild.getMeasuredWidth(),releasedChild.getTop());
                    ViewCompat.postInvalidateOnAnimation(MyDragHelper.this);
                }
            }
        });
       // mDragger.setEdgeTrackingEnabled();


    }

    @Override
    public void computeScroll() {               //开启平滑滚动时回调的方法
        if (mDragger.continueSettling(true)){     // 判断当前是否仍在执行动画
            ViewCompat.postInvalidateOnAnimation(MyDragHelper.this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        firstView = getChildAt(0);
        secondView = getChildAt(1);
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
