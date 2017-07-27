package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 31716 on 2017/7/24.
 */

/**
 * path相关知识
 */
public class PathView extends View {
    private Paint paint;
    private int mWidth ;
    private int mHeight ;
    private Path path;

    public PathView(Context context) {
        super(context);
        initPaint();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    private void initPaint() {
        path = new Path();
        paint = new Paint();
        paint.setStrokeWidth(3f);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }
    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.translate(mWidth / 2, mHeight / 2);
//       path.lineTo(200,200);
//       path.lineTo(200,0);
//        path.close();
//        canvas.drawPath(path,paint);
//        path.addCircle(0,0,200, Path.Direction.CW);
//        canvas.drawPath(path,paint);
        path.lineTo(100, 100);
        RectF rect = new RectF(100, 100, 300, 300);
        path.arcTo(rect, -90, 180, false); // 直接连线连到弧形起点（有痕迹）
        canvas.drawPath(path,paint);
    }
}
