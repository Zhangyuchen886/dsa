package com.jiyun.zuoye1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg;
    private TextView mTv;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    int i = 4;

    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mTv = (TextView) findViewById(R.id.tv);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.donghua);
        animation.setDuration(3000);
        mImg.startAnimation(animation);
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                i--;
                mTv.setText("倒计时" + i);

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));

            }
        }.start();
    }
}
