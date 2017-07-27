package xk.viewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by 31716 on 2017/7/27.
 */

public class CirclePercentView extends View {
    /**
     * 色带宽度
     */
    private float mStripeWidth ;
    /**
     * 圆形半径
     */
    private float mRadius ;
    /**
     * 当前进度百分比
     */
    private int mCurPercent ;

    private int mSmallColor ;       //小圆颜色
    private int mBigColor ;     //大圆颜色
    private float mCenterTextSize ;   //文字大小
    private Paint textPaint;
    private Paint stripePaint;
    private String centerText = "";
Rect rect ;
    public CirclePercentView(Context context) {
        this(context,null);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义属性
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.CirclePercentView, defStyleAttr, 0);

        //获取色带的宽度
        mStripeWidth = a.getDimension(R.styleable.CirclePercentView_stripeWidth, 30);
        //获取当前的百分比
        mCurPercent = a.getInteger(R.styleable.CirclePercentView_percent, 0);
        //获取小园的颜色
        mSmallColor = a.getColor(R.styleable.CirclePercentView_smallColor,0xffafb4db);
        //获取大圆的颜色
        mBigColor = a.getColor(R.styleable.CirclePercentView_bigColor,0xff6950a1);
        //获取中心文字的大小
        mCenterTextSize = a.getDimensionPixelSize(R.styleable.CirclePercentView_textSize,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        //获取园的半径
        mRadius = a.getDimensionPixelSize(R.styleable.CirclePercentView_circle_radius,200);
        a.recycle();

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(mCenterTextSize);

        stripePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        stripePaint.setStrokeWidth(mStripeWidth);
        stripePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        rect = new Rect();
        textPaint.getTextBounds(mCurPercent+" %",0,(mCurPercent+" %").length(),rect);
    }
    int mWidth;
    int mHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);

        stripePaint.setColor(mBigColor);
        canvas.drawCircle(0,0,mRadius,stripePaint);

        stripePaint.setStyle(Paint.Style.STROKE);
        stripePaint.setColor(mSmallColor);
        canvas.drawArc(-200,-200,200,200,-90,360*mCurPercent/100f,false,stripePaint);

        canvas.drawText(mCurPercent+" %",-rect.width()/2,rect.height()/2,textPaint);
    }

    public void setmCurPercent(int mCurPercent) {
        this.mCurPercent = mCurPercent;
       postInvalidate();
    }
}
