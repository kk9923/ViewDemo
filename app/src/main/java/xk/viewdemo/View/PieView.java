package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import xk.viewdemo.bean.PieChartInfo;

/**
 * Created by 31716 on 2017/7/17.
 */

/**
 * 自定义饼状图
 */
public class PieView extends View {
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    private Paint paint;
    private ArrayList<PieChartInfo> pieChartInfos = new ArrayList<>();
    // 宽高
    private int mWidth, mHeight;
    private RectF rect;

    public PieView(Context context) {
        super(context);
        initPaint();
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1f);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentStartAngle = mStartAngle;                    // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        // 饼状图绘制区域
        if (rect ==null){
             rect = new RectF(-r, -r, r, r);
        }
        for (int i = 0; i < pieChartInfos.size(); i++) {
            PieChartInfo pie = pieChartInfos.get(i);
            paint.setColor(pie.getColor());
            canvas.drawArc(rect,currentStartAngle,pie.getAngle(),true,paint);
            currentStartAngle +=pie.getAngle();

        }
    }

    public void setPieDatas(ArrayList<PieChartInfo> pieChartInfos) {
        this.pieChartInfos = pieChartInfos;
        float sumValue = 0 ;
        for (int i = 0; i < pieChartInfos.size(); i++) {
            PieChartInfo pieChartInfo = pieChartInfos.get(i);
            float value = pieChartInfo.getValue();
            sumValue  += value;
            int j = i % mColors.length;       //设置颜色
            pieChartInfo.setColor(mColors[j]);

        }
        float sumAngle = 0;
        for (int i = 0; i < pieChartInfos.size(); i++) {
            PieChartInfo pieChartInfo = pieChartInfos.get(i);
            float value = pieChartInfo.getValue();
            float percentage = pieChartInfo.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pieChartInfo.setPercentage(percentage);                  // 记录百分比
            pieChartInfo.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;
        }
        invalidate();
    }
    OnPieChartItemClickListener clickListener ;

    public void setOnPieChartItemClickListener(OnPieChartItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface OnPieChartItemClickListener {
        void onPieItemViewClick(int position);
    }
}
