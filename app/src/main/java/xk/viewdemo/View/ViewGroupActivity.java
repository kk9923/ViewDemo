package xk.viewdemo.View;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

import xk.viewdemo.R;

public class ViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawlayout);
        float [] matrixs = {2f,1.0f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f};
        Matrix matrix = new Matrix();
     //   matrix.setValues(matrixs);
      //  System.out.println(matrix.toShortString());
        float [] matrixs2 = new float[9];
     //   matrix.getValues(matrixs2);
     //  System.out.println(matrixs2[0]);

        // 初始数据为三个点 (0, 0) (80, 100) (400, 300)
        float[] src = new float[]{0, 0, 80, 100, 400, 300};
        float [] pts =  new float[6];

        matrix.setScale(0.5f,1.0f);

        matrix.mapPoints(pts,1,src,2,2);

        System.out.println(Arrays.toString(pts));
        System.out.println(Arrays.toString(src));

    }
}
