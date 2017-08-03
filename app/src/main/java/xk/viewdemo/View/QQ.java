package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.FloatEvaluator;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by 31716 on 2017/8/1.
 */

public class QQ extends FrameLayout {
    private ViewDragHelper dragHelper;
    private View mainView;
    private View menuView;
    private int width ;
    private float dragRange ;
    private FloatEvaluator floatEvaluator;//float的计算器
    private IntEvaluator intEvaluator;//int的计算器
    public QQ(@NonNull Context context) {
        super(context);
        init();
    }
    public QQ(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public QQ(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        floatEvaluator = new FloatEvaluator();
        intEvaluator = new IntEvaluator();
        dragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mainView || child == menuView;
            }
            @Override
            public int getViewHorizontalDragRange(View child) {
                return (int) dragRange;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if(child==mainView){
                    if(left<0)left=0;//限制mainView的左边
                    if(left>dragRange)left=(int) dragRange;//限制mainView的右边
                }
                return left;
            }
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                if (changedView == menuView){
                    menuView.layout(0,0,menuView.getMeasuredWidth(),menuView.getMeasuredHeight());
                    //让mainView移动起来
                    int newLeft = mainView.getLeft()+dx;
                    if(newLeft<0)newLeft=0;//限制mainView的左边
                    if(newLeft>dragRange)newLeft=(int) dragRange;//限制mainView的右边
                    mainView.layout(newLeft,mainView.getTop()+dy,newLeft+mainView.getMeasuredWidth(),mainView.getBottom()+dy);
                }
                //1.计算滑动的百分比
                float fraction = mainView.getLeft()/dragRange;
                //2.执行伴随动画
                executeAnim(fraction);
            }



            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (mainView.getLeft() > dragRange/2){
                    open();
                }else {
                    close();
                }
            }
        });
    }

    private void executeAnim(float fraction) {
        //缩小mainView
//		float scaleValue = 0.8f+0.2f*(1-fraction);//1-0.8f
        ViewHelper.setScaleX(mainView, floatEvaluator.evaluate(fraction,1f,0.8f));
        ViewHelper.setScaleY(mainView, floatEvaluator.evaluate(fraction,1f,0.8f));
        //移动menuView
        ViewHelper.setTranslationX(menuView,intEvaluator.evaluate(fraction,-menuView.getMeasuredWidth()/2,0));
        //放大menuView
        ViewHelper.setScaleX(menuView,floatEvaluator.evaluate(fraction,0.5f,1f));
        ViewHelper.setScaleY(menuView,floatEvaluator.evaluate(fraction,0.5f,1f));
        //改变menuView的透明度
        ViewHelper.setAlpha(menuView,floatEvaluator.evaluate(fraction,0.3f,1f));

        //给SlideMenu的背景添加黑色的遮罩效果
        getBackground().setColorFilter((Integer) ColorUtil.evaluateColor(fraction, Color.BLACK,Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
    }

    private void open() {
        dragHelper.smoothSlideViewTo(mainView, (int) dragRange,0);
        ViewCompat.postInvalidateOnAnimation(mainView);
    }
    private void close() {
        dragHelper.smoothSlideViewTo(mainView, 0,0);
        ViewCompat.postInvalidateOnAnimation(mainView);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(mainView);
        }
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        dragRange = width *0.6f;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() >2){
          throw   new IllegalArgumentException("QQ can only have 2 children!");
        }
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }
}
