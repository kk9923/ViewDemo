package xk.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 31716 on 2017/7/27.
 */

public class PopupView extends View {

    private Paint paint;
    private int width;
    private int height;
    private Path path;

    public PopupView(Context context) {
        this(context,null);
    }

    public PopupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PopupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        path = new Path();
        path.moveTo(20,100);
        path.lineTo(0,130);
        path.lineTo(-20,100);
        path.close();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        width = 0;
        height = 0;
        if (widthMode ==MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            width = (int) (mWidth* 0.8);
        }
        if (heightMode ==MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            height = (int) (mHeight* 0.8);
        }
        setMeasuredDimension(width, height);
    }
    int mWidth;
    int mHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawRoundRect(-200,-100,200,100,10,10,paint);
        canvas.drawPath(path,paint);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(-width/2,-height/2,width/2,height/2,10,10,paint);
    }
}
