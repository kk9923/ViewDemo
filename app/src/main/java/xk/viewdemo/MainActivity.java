package xk.viewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

import xk.viewdemo.View.PieChartInfo;
import xk.viewdemo.View.PieView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PieView viewById = (PieView) findViewById(R.id.pieeview);
        ArrayList<PieChartInfo> pieChartInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieChartInfo  pieChartInfo = new PieChartInfo("2", new Random().nextInt(5)*10);
            pieChartInfos.add(pieChartInfo);
        }
        viewById.setPieDatas(pieChartInfos);
    }
}
