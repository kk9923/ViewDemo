package xk.viewdemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import xk.viewdemo.R;

/**
 * Created by 31716 on 2017/7/25.
 */

/**
 * Paint相关操作
 */
public class PaintView extends View {

    private Paint paint;

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.FILL);
       // Shader shader = new LinearGradient(250, 250, 300, 300, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
      //  Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.single);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(300,300,100,paint);
        paint.setTextSize(50);
        paint.setStrikeThruText(true);  // 在文字中间设置水平横线
        paint.setUnderlineText(true);   //设置下划线
        paint.setTextSkewX(-2);     //  设置文字倾斜度
        paint.setLetterSpacing(1f);
        canvas.drawText("测试Paint",100,100,paint);
    }
}
