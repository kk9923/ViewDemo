package xk.viewdemo.View;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 31716 on 2017/8/3.
 */

public class MyDrawLayout extends FrameLayout {
    private ViewDragHelper dragHelper ;
    private View menuView;
    private View mainView;
    private int mScreenWidth ;
    private double menuViewPercent = 0.8 ;

    public MyDrawLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public MyDrawLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDrawLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dragHelper = ViewDragHelper.create(this, new MyCallBack());
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }
    class  MyCallBack extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //返回ture则表示可以捕获该view,手指摸上一瞬间调运
            return child==menuView;
        }
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            //setEdgeTrackingEnabled设置的边界滑动时触发
            //captureChildView是为了让tryCaptureView返回false依旧生效
            dragHelper.captureChildView(menuView, pointerId);
        }
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //手指触摸移动时实时回调, left表示要到的x位置
            if (left>0){
                left=0;
            }
            if (left<  - menuViewPercent * mScreenWidth){
                left  = (int) (-menuViewPercent*mScreenWidth);
            }
            return left;
        }
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenWidth = w;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainView = getChildAt(0);
        menuView = getChildAt(1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }
}
