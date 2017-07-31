package xk.viewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by 31716 on 2017/7/29.
 */

public class MyLinearLayout extends ViewGroup {
    public MyLinearLayout(Context context) {
        this(context,null);
    }
    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LinearLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 当宽高是wrap_content时用来记录    宽和高
         */
        int width = 0;
        int height = 0;

        int childCount = getChildCount();
        int cWidth = 0;   //用于记录每个子View的宽度
        int cHeight = 0;    //用于记录每个子View的高度
        LinearLayout.LayoutParams cParams = null;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (LinearLayout.LayoutParams) childView.getLayoutParams();

           width += cWidth + cParams.leftMargin+cParams.leftMargin;  // 将所有子View的宽度相加
        }

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width, (heightMode == MeasureSpec.EXACTLY) ? heightSize : 300);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int cWidth = 0;   //用于记录每个子View的宽度
        int cHeight = 0;    //用于记录每个子View的高度
        LinearLayout.LayoutParams cParams = null;

        int width =0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            System.out.println("cWidth= "  +cWidth + "    cHeight =  " + cHeight);
            cParams = (LinearLayout.LayoutParams) childView.getLayoutParams();

            int left = 0, top = 0, right = 0, bottom = 0;     //用来记录子View的坐标， 左  上  右  下

            width += cWidth + cParams.leftMargin+cParams.rightMargin;
            switch (i){
                case 0:
                    left = cParams.leftMargin;
                    top = cParams.topMargin;
                    break;
                case 1:
                    left = getWidth()-cWidth-cParams.rightMargin-cParams.leftMargin;
                    top = cParams.topMargin;
                    break;
//                case 2:
//                    left = cParams.leftMargin;
//                    top = cParams.topMargin;
//                    break;
            }

            right = left+cWidth;
            bottom = top+cHeight;
            childView.layout(left, top, right, bottom);
        }
    }
}
