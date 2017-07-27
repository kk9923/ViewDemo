package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 31716 on 2017/7/27.
 */

/**
 * 手指轨迹
 */
public class MyTextView extends View {

    private Paint paint;
    private Path path;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawPath(path,paint);
    }

    /**
     * 使用lineTo 实现
     * @param event
     * @return
     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:{
//                path.moveTo(event.getX(),event.getY());
//                return  true ;
//            }
//            case MotionEvent.ACTION_MOVE:{
//                path.lineTo(event.getX(),event.getY());
//                invalidate();
//                break;
//            }
//        }
//        return super.onTouchEvent(event);
//    }

    /**
     * 使用贝塞尔曲线 quadTo 实现
     * @param event
     * @return
     */
    private  float mPreX ;
    private  float mPreY ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                mPreX = event.getX();
                mPreY = event.getY();
                path.moveTo(event.getX(),event.getY());
                return  true ;
            }
            case MotionEvent.ACTION_MOVE:{
                float endX = (mPreX+event.getX())/2;        //起点和移动点的中点x坐标
                float endY = (mPreY+event.getY())/2;        //起点和移动点的中点Y坐标
                path.quadTo(mPreX,mPreY,endX,endY);         //在起点和中点之间绘制贝塞尔曲线
                mPreX = event.getX();
                mPreY =event.getY();
                invalidate();
                break;
            }
        }
        return super.onTouchEvent(event);
    }

}
