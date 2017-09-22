package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 31716 on 2017/8/11.
 */

public class FoldLayout extends ViewGroup {
    private static final int NUM_OF_POINT = 8;
    /**
     * 图片的折叠后的总宽度
     */
    private float mTranslateDis;
    //折叠后的总宽度与原图宽度的比例
    protected float mFactor = 0.8f;
    private int mNumOfFolds = 8;
    private Matrix[] mMatrices = new Matrix[mNumOfFolds];
    private Paint mSolidPaint;
    private Paint mShadowPaint;
    private Matrix mShadowGradientMatrix;
    private LinearGradient mShadowGradientShader;
    private int mHeight ;
    private int mWidth ;
    /***
     * 原图每块的宽度
     */
    private float mFlodWidth;
    /**
     * 折叠时，每块的宽度
     */
    private float mTranslateDisPerFlod;
    private Canvas mCanvas = new Canvas();
    private Bitmap mBitmap;
    private boolean isReady;
    private float[] src;
    private float[] dst;
    private float depth;

    public FoldLayout(Context context) {
        this(context,null);
    }
    public FoldLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public FoldLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        for (int i = 0; i < mNumOfFolds; i++) {
            mMatrices[i] = new Matrix();
        }
        mSolidPaint = new Paint();
        mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowGradientShader = new LinearGradient(0, 0, 0.5f, 0, Color.BLACK,
                Color.TRANSPARENT, Shader.TileMode.CLAMP);
        mShadowPaint.setShader(mShadowGradientShader);
        mShadowGradientMatrix = new Matrix();
        this.setWillNotDraw(false);
        int alpha = (int) (255 * (1 - mFactor));
        mSolidPaint.setColor(Color.argb((int) (alpha * 0.8F), 0, 0, 0));
        depth = (float) (Math.sqrt(mFlodWidth * mFlodWidth - mTranslateDisPerFlod * mTranslateDisPerFlod) / 2);
        src = new float[NUM_OF_POINT];
        dst = new float[NUM_OF_POINT];
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child = getChildAt(0);
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(child.getMeasuredWidth(),
                child.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child = getChildAt(0);
        child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        updateFold();
    }

    private void updateFold() {
        mTranslateDis = getMeasuredWidth() * mFactor;
        mFlodWidth = getMeasuredWidth()/ mNumOfFolds;
        mTranslateDisPerFlod = mTranslateDis /mNumOfFolds;
        for (int i = 0; i < mNumOfFolds; i++) {
            src[0] = i * mFlodWidth;
            src[1] = 0;
            src[2] = src[0] + mFlodWidth;
            src[3] = 0;
            src[4] = src[2];
            src[5] = mBitmap.getHeight();
            src[6] = src[0];
            src[7] = src[5];

            boolean isEven = i % 2 == 0;

            dst[0] = i * mTranslateDisPerFlod;
            dst[1] = isEven ? 0 : depth;

            dst[2] = dst[0] + mTranslateDisPerFlod;
            dst[3] = isEven ? depth : 0;
            dst[4] = dst[2];
            dst[5] = isEven ? mBitmap.getHeight() - depth : mBitmap.getHeight();
            dst[6] = dst[0];
            dst[7] = isEven ? mBitmap.getHeight() : mBitmap.getHeight() - depth;
            mMatrices[i].setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mFactor == 0)
            return;
        if (mFactor == 1)
        {
            super.dispatchDraw(canvas);
            return;
        }
        for (int i = 0; i < 8; i++) {
            canvas.save();
            canvas.concat(mMatrices[i]);
            canvas.clipRect(i* mFlodWidth,0,(i+1)*mFlodWidth,mHeight);
            canvas.drawBitmap(mBitmap, 0, 0, null);
            if (isReady) {
                canvas.drawBitmap(mBitmap, 0, 0, null);
            } else {
                // super.dispatchDraw(canvas);
                super.dispatchDraw(mCanvas);
                canvas.drawBitmap(mBitmap, 0, 0, null);
                isReady = true;
            }
            canvas.translate(mFlodWidth * i, 0);
            if ( i %2== 0 ){
                canvas.drawRect(0, 0, mFlodWidth, getHeight(), mSolidPaint);
            }
            canvas.restore();

        }
    }
    public void setFactor(float factor) {
        this.mFactor = factor;
        updateFold();
        invalidate();
    }

    public float getFactor() {
        return mFactor;
    }
}
