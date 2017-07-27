package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 31716 on 2017/7/24.
 */

/**
 * 画布的基本操作
 */
public class CanvasBasicOptionView extends View {

    private Paint paint;
    private int mWidth ;
    private int mHeight ;

    public CanvasBasicOptionView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasBasicOptionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CanvasBasicOptionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3f);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    /**
     * 平移画布
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        /**
//         * 将画布定点平移至200，200处
//         */
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,paint);
//        paint.setColor(Color.GREEN);
//        canvas.translate(400,0);
//        canvas.drawCircle(0,0,100,paint);
//        canvas.translate(-200,400);
//        paint.setColor(Color.BLACK);
//        canvas.drawCircle(0,0,100,paint);
//    }
    /**
     * 画布缩放
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);
//
//        RectF rect = new RectF(0,-200,200,0);   // 矩形区域
//
//        paint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,paint);
//
//        canvas.scale(0.5f,0.5f);                // 画布缩放
//
//        paint.setColor(Color.RED);            // 绘制红色矩形
//        canvas.drawRect(rect,paint);
//
//        canvas.scale(-2f,2f,0,0);                // 画布缩放
//
//        paint.setColor(Color.GREEN);            // 绘制红色矩形
//        canvas.drawRect(rect,paint);

       // canvas.drawLine();

        // 将坐标系原点移动到画布正中心
      //  canvas.translate(mWidth / 2, mHeight / 2);

//        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域
//        for (int i=0; i<=20; i++) {
//            canvas.scale(0.9f,0.9f);
//            canvas.drawRect(rect,paint);
//        }
        canvas.drawCircle(0,0,300,paint);
        canvas.drawCircle(0,0,280,paint);
        for (int i = 0; i < 18; i++) {
            canvas.drawLine(280,0,300,0,paint);
            canvas.rotate(20);
        }
    }
}
