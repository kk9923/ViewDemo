package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 31716 on 2017/7/26.
 */

public class SearchViewTest extends View {

    // 画笔
    private Paint mPaint;
    // View 宽高
    private int mViewWidth;
    private int mViewHeight;
    // 放大镜与外部圆环
    private Path path_search;
    private Path path_circle;
    public SearchViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public SearchViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        PathMeasure pathMeasure = new PathMeasure();

         path_search = new Path();
         path_circle = new Path();
        RectF rect_search = new RectF(-50,-50,50,50);
        RectF rect_circle = new RectF(-100,-100,100,100);

        path_search.addArc(rect_search,45f,359.9f);   // 内部圆环

        path_circle.addArc(rect_circle,45f,359.9f);     //添加外部圆环
        pathMeasure.setPath(path_circle,false);  // 将path与pathMeasure绑定，该path从45度为起点
        float [] xys = new float[2];
        pathMeasure.getPosTan(0,xys,null);    // 获取当前path长度为0点的坐标
        Log.i("xx","x = " + xys[0]+"y =" + xys[1]);
        path_search.lineTo(xys[0],xys[1]);    //添加把手
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
      canvas.translate(mViewWidth/2,mViewHeight/2);
        canvas.drawPath(path_circle,mPaint);
        canvas.drawPath(path_search,mPaint);

    }
}
