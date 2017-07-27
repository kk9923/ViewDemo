package xk.viewdemo.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by qijian on 16/3/21.
 */
public class MyView extends View {
    private Paint mPaint,paint2;
    private Path mPath,path2;
    private int mItemWaveLength = 1000;
    private int dx,dy;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        path2 = new Path();
        mPaint = new Paint();
        paint2 = new Paint();
        mPaint.setColor(Color.GREEN);
        paint2.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        dy = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //path2.reset();
        int originY = 100;
        int halfWaveLen = mItemWaveLength/2;  // 半个波长
        //博客中代码,不向下移动
        mPath.moveTo(-mItemWaveLength+dx,originY);
       // path2.moveTo(-mItemWaveLength+dx,120);

        //实现向下移动动画
      //  mPath.moveTo(-mItemWaveLength+dx,originY+dy);
      //  dy += 1;
        int count = 1 ;
       for (int i = -mItemWaveLength;i<=getWidth()+mItemWaveLength;i+=mItemWaveLength){
           System.out.println(i);
           System.out.println(count);
           count++;
            mPath.rQuadTo(halfWaveLen/2,-100,halfWaveLen,0);
            mPath.rQuadTo(halfWaveLen/2,100,halfWaveLen,0);
         //   path2.rQuadTo(halfWaveLen/2,-100,halfWaveLen,0);
          //  path2.rQuadTo(halfWaveLen/2,100,halfWaveLen,0);
        }
        mPath.lineTo(getWidth(),getHeight());
      //  path2.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
      //  path2.lineTo(0,getHeight());
      //  mPath.close();
        //mPath.rQuadTo();
        canvas.drawPath(mPath,mPaint);
        canvas.drawLine(0,100,1000,100,mPaint);
        System.out.println(count);
      //  canvas.drawPath(path2,paint2);

    }

    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
