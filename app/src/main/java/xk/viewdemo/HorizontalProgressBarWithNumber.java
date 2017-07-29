package xk.viewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by 31716 on 2017/7/28.
 */

public class HorizontalProgressBarWithNumber extends View {
    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFd3d6da;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;
        private Rect  mReachedRect ;
    protected Paint mPaint = new Paint();

    protected int mTextColor = DEFAULT_TEXT_COLOR;      //文字进度颜色

    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);     //文字大小

    protected int mTextOffset = dp2px(DEFAULT_SIZE_TEXT_OFFSET);    // 文字左右偏移距离

    protected int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);  //左边进度条高度

    protected int mReachedBarColor = DEFAULT_TEXT_COLOR;        //左边进度条颜色

    protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;   //右边进度条颜色

    protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);  //左边进度条高度
    protected boolean mIfDrawText = true;

    protected static final int VISIBLE = 0;
    private float textHeight;


    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setHorizontalScrollBarEnabled(true);
        obtainStyledAttributes(attrs);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        mReachedRect = new Rect();
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        textHeight = fontMetrics.descent - fontMetrics.ascent;
        mPaint.getTextBounds(currentPercent+"%",0,(currentPercent+"%").length(),mReachedRect);
    }
    private void obtainStyledAttributes(AttributeSet attrs) {
        final TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBarWithNumber);

        mTextColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_text_color, DEFAULT_TEXT_COLOR);
        mTextSize = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_size, mTextSize);

        mReachedBarColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_reached_color, mTextColor);
        mUnReachedBarColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_unreached_color, DEFAULT_COLOR_UNREACHED_COLOR);
        mReachedProgressBarHeight = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_reached_bar_height, mReachedProgressBarHeight);
        mUnReachedProgressBarHeight = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_unreached_bar_height, mUnReachedProgressBarHeight);
        mTextOffset = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_offset, mTextOffset);
        int textVisible = attributes.getInt(R.styleable.HorizontalProgressBarWithNumber_progress_text_visibility, VISIBLE);
        if (textVisible != VISIBLE) {
            mIfDrawText = false;
        }
        attributes.recycle();
    }
    private float barWidth = 500;
    private  int currentPercent = 10;
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(100,500);
       // canvas.drawLine(0,-300,0,500,mPaint);
      //  canvas.drawLine(barWidth,-300,barWidth,500,mPaint);
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        float leftWidth = (currentPercent / 100f) * barWidth + mTextOffset + mReachedRect.width();

        if (leftWidth >= barWidth) {
            //绘制左边区域
            canvas.drawLine(0,0,barWidth-(mTextOffset + mReachedRect.width()),0,mPaint);
        }else {
            //绘制左边区域
            canvas.drawLine(0,0,currentPercent /100f* barWidth,0,mPaint);
        }

        if (leftWidth >= barWidth){
            canvas.drawText(currentPercent+"%", barWidth- mReachedRect.width()-mTextOffset,mReachedRect.height()/2,mPaint);
        }else {
            //绘制中间文字区域
            canvas.drawText(currentPercent+"%",currentPercent /100f * barWidth+mTextOffset,mReachedRect.height()/2,mPaint);
        }
        //绘制右边区域
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
       // if (leftWidth >= barWidth){

      //  }else {
             canvas.drawLine((currentPercent/100f)* barWidth+2*mTextOffset+mReachedRect.width(),0,barWidth,0,mPaint);
     //   }
       // System.out.println("左边=  "  + (barWidth-(mTextOffset + mReachedRect.width())));
      //  System.out.println("中间=  "  + (mTextOffset+mReachedRect.width()) );
      //  System.out.println("右边=  "  + (barWidth-((currentPercent/100f) * barWidth+2*mTextOffset+mReachedRect.width())));

    }

    public void setCurrentPercent(int currentPercent) {
        this.currentPercent = currentPercent;
        invalidate();
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }
}
