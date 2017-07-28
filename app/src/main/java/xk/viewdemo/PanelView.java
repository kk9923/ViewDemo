package xk.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 31716 on 2017/7/28.
 */

public class PanelView extends View {
    private int mWidth;
    private int mHeight;
    private int mPercent = 0 ;
    //刻度宽度
    private float mTikeWidth;
    //第二个弧的宽度
    private int mScendArcWidth;
    //最小圆的半径
    private int mMinCircleRadius;
    //文字矩形的宽
    private int mRectWidth;
    //文字矩形的高
    private int mRectHeight;
    //文字内容
    private String mText = "";
    //文字的大小
    private int mTextSize;
    //设置文字颜色
    private int mTextColor;
    private int mArcColor;
    //小圆和指针颜色
    private int mMinCircleColor;
    //刻度的个数
    private int mTikeCount;
    private Context mContext;
    private Paint p,p2;

    public PanelView(Context context) {
        this(context,null);
    }

    public PanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public PanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        p = new Paint();
        int strokeWidth = 3;
        p.setStrokeWidth(strokeWidth);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.parseColor("#5FB1ED"));
        p2 = new Paint();
        p2.setStrokeWidth(45);
        p2.setAntiAlias(true);
        p2.setStyle(Paint.Style.STROKE);
        p2.setColor(Color.WHITE);
    }
    RectF rect1 = new RectF(-200,-200,200,200);
    RectF rect2 = new RectF(-200,-200,200,200);
    RectF rect3 = new RectF(-150,-150,150,150);
    RectF rect4 = new RectF(-200,-200,200,200);
    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawColor(Color.BLACK);
        canvas.translate(mWidth/2,mHeight/2);
        p.setColor(Color.parseColor("#5FB1ED"));
        //1.最外层圆弧
        canvas.drawArc(rect1,145,250,false,p);
    //    p.setColor(Color.parseColor("#5FB1ED"));

       // 2.里面的粗弧
        p.setStrokeWidth(45);
       if (mPercent==0){
         canvas.drawArc(rect3,145,250,false,p2);
       }else {
           canvas.drawArc(rect3,145+250 * (mPercent /100f),250 * ((100-mPercent )/100f),false,p2);        //白色部分
           canvas.drawArc(rect3,145,250 * (mPercent /100f),false,p);        //蓝色部分
       }
        //3.中间粗圆
       // p.setColor(Color.WHITE);
        canvas.drawCircle(0,0,3,p);
       // 4.最小细圆
        p.setColor(Color.parseColor("#5FB1ED"));
        p.setStrokeWidth(3);
        canvas.drawCircle(0,0,35,p);
       // 5.刻度
        float itemAngle = 250 / 12f;

        canvas.rotate(35);

        canvas.drawLine(180,0,200,0,p);
        for (int i = 0; i < 12; i++) {
            canvas.rotate(-itemAngle);
            canvas.drawLine(180,0,200,0,p);
        }
        for (int i = 0; i < 12; i++) {
            canvas.rotate(itemAngle);
        }
        canvas.rotate(-35);
        p.setColor(Color.RED);
        p.setStrokeWidth(6);
        float x = (float) Math.cos((145+(mPercent / 100f)*250)*Math.PI / 180);

        float y = (float) Math.sin((145+(mPercent / 100f)*250)*Math.PI / 180);
        canvas.drawLine(25*x,25*y,130*x,130*y,p);
       // System.out.println(0+(mPercent / 100f)*250 );
       // 6.指针

    }

    public void setmPercent(int mPercent) {
        this.mPercent = mPercent;
        invalidate();
    }
}
