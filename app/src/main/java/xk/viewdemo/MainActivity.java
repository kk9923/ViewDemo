package xk.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    //private CircleChartView chartView;
    int currentPercent = 0;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentPercent++;
            if (currentPercent <=100){
                circle.setmCurPercent(currentPercent);
            }else {
                currentPercent = 0 ;
                circle.setmCurPercent(currentPercent);
            }
            circle.postDelayed(runnable,300);
        }
    };
    private CirclePercentView circle;
    private PanelView panelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circle = (CirclePercentView) findViewById(R.id.circle);
        SeekBar seekBar  = (SeekBar) findViewById(R.id.seekBar);
        panelView = (PanelView) findViewById(R.id.panelView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                panelView.setmPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        circle.postDelayed(runnable,1000);
      //  MyView myview = (MyView) findViewById(R.id.myview);
      //  myview.startAnim();
       // chartView = (CircleChartView) findViewById(R.id.circle_view);
       // init();
        //饼状图
//        PieView viewById = (PieView) findViewById(R.id.pieeview);
//        ArrayList<PieChartInfo> pieChartInfos = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            PieChartInfo  pieChartInfo = new PieChartInfo("2", new Random().nextInt(5)*10);
//            pieChartInfos.add(pieChartInfo);
//        }
//        viewById.setPieDatas(pieChartInfos);
       // CanvasBasicOptionView canvasOption = (CanvasBasicOptionView) findViewById(R.id.canvasOption);

    }
//    private void init() {
//        Button scale = (Button) findViewById(R.id.scale);
//        Button alphaa = (Button) findViewById(R.id.alphaa);
//        Button roratea = (Button) findViewById(R.id.roratea);
//        Button translate = (Button) findViewById(R.id.translate);
//        Button set = (Button) findViewById(R.id.set);
//        final ImageView iv = (ImageView) findViewById(R.id.iv);
//        scale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.scaleanim));
//            }
//        });
//        alphaa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.alphaanim));
//            }
//        });
//        roratea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotateanim));
//            }
//        });
//        translate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.translateanim));
//                ValueAnimator valueAnimator = ValueAnimator.ofInt(1,400,50,300);   // 先从1 到400 ，在从400到50 ，在从50到300
//                valueAnimator.setDuration(3000);
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                      int value = (int) animation.getAnimatedValue();
//                        iv.layout(value,value,value+iv.getWidth(),value+iv.getHeight());
//                    }
//                });
//                valueAnimator.setInterpolator(new LinearInterpolator());
//                valueAnimator.start();
//            }
//        }); set.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.setanim));
//            }
//        });
//    }

}
