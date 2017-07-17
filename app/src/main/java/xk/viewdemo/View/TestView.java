package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static android.graphics.Paint.Style.STROKE;

/**
 * Created by 31716 on 2017/7/17.
 */

public class TestView extends View {
    private Paint paint = new Paint();
    public TestView(Context context) {
        super(context);
        initPaint();
    }
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始画笔
     */
    private void initPaint() {
        paint.setColor(Color.RED);  // 画笔颜色
        paint.setStrokeWidth(2f);  //  画笔宽度
        paint.setStyle(STROKE);  //设置画笔模式为填充
        //STROKE                //描边
        // FILL                  //填充
        //FILL_AND_STROKE       //描边加填充
    }

    /**
     * 直接根据点的坐标绘制点
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //一次绘制单个点
//        canvas.drawPoint(100,200,paint);
//        canvas.drawPoint(200,200,paint);
//        canvas.drawPoint(300,200,paint);
//    }

    /**
     * 根据点坐标的集合来绘制多个点
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawPoints(new float[]{100,200,300,400},paint);
//    }

    /**
     * 绘制一条直线
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //参数   起点x  ,起点y ，  终点x  终点y
//        canvas.drawLine(100,100,200,200,paint);
//    }

    /**
     *  绘制一组线 每四数字(两个点的坐标)确定一条线
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        float [] pts = new float[]{100,100,200,300,400,400,500,600};
//        canvas.drawLines(pts,paint);
//    }
    /**
     * 绘制矩形  两者最大的区别就是精度不同，Rect是int(整形)的，而RectF是float(单精度浮点型)的
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //根据点的坐标绘制矩形
//        canvas.drawRect(100,100,200,200,paint);
//        canvas.drawLine(200,100,200,600,paint);
//        Rect rect = new Rect(200,200,300,300);   //最好不要在onDraw方法中去new 对象 （onDraw方法可能会多次调用，就会多次创建对象）
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(rect,paint);
//        // 第三种
//        paint.setColor(Color.RED);
//        RectF rectF = new RectF(300,300,400,400);
//        canvas.drawRect(rectF,paint);
//    }
    /**
     * 绘制圆角矩形   圆角矩形的角实际上不是一个正圆的圆弧，而是椭圆的圆弧，这里的两个参数实际上是椭圆的两个半径，rx  和  ry
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        // 第一种
//        RectF rectF = new RectF(100,100,300,400);
//        // 绘制背景矩形
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,paint);
//        paint.setColor(Color.RED);
//        canvas.drawRoundRect(rectF,500,500,paint);
//        // 第二种
//     //   canvas.drawRoundRect(100,100,300,400,30,30,paint);
//    }
    /**
     * 绘制椭圆  绘制椭圆实际上就是绘制一个矩形的内切图形
     * PS： 如果你传递进来的是一个长宽相等的矩形(即正方形)，那么绘制出来的实际上就是一个圆。
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        // 第一种
//        RectF rectF = new RectF(100,100,300,400);
//        canvas.drawOval(rectF,paint);
//    }
    /**
     * 绘制圆  绘制圆形有四个参数，前两个是圆心坐标，第三个是半径，最后一个是画笔。
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,100,paint);
    }

    /**
     * 绘制圆弧  根据矩形区域来绘制圆弧   参数   矩形坐标     起始角度， 扫过角度（圆弧角度）  ，是否使用中心 ， 画笔
     * 是否使用中心 ，  true  根据起始角度和圆弧角度绘制一段扇形
     * 是否使用中心 ，  false  根据起始角度和圆弧角度绘制一段弓形
     */

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        RectF rectF = new RectF(100,100,400,400);
//        // 绘制背景矩形
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,paint);
//
//        // 绘制圆弧
//        paint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,30,60,true,paint);
//        paint.setColor(Color.RED);
//        canvas.drawArc(rectF,90,90,false,paint);
//    }
}
