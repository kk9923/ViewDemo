package xk.viewdemo.View;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import xk.viewdemo.MatrixSetPolyToPolyTest;
import xk.viewdemo.R;

public class MartriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martri);
        final MatrixSetPolyToPolyTest poly = (MatrixSetPolyToPolyTest) findViewById(R.id.poly);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb1 :


                        break;
                    case R.id.rb2 :


                        break;
                    case R.id.rb3 :


                        break;
                    case R.id.rb4 :


                        break;
                }
            }
        });

    }
}
