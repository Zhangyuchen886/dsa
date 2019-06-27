package com.jiyun.animtest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * alpha                            渐变透明度动画效果
     */
    private Button mBtn1;
    /**
     * scale                            渐变尺寸伸缩动画效果
     */
    private Button mBtn2;
    /**
     * translate                        画面转换位置移动动画效果
     */
    private Button mBtn3;
    /**
     * rotate                           画面转移旋转动画效果
     */
    private Button mBtn4;
    private ImageView mImg;
    private ProgressBar mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);

        mImg = (ImageView) findViewById(R.id.img);
        mBar = (ProgressBar) findViewById(R.id.bar);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(mImg, "alpha", 1.0f, 0.0f, 0.7f).setDuration(3000).start();
                ObjectAnimator.ofFloat(mImg, "scaleX", 1.0f, 3.0f,1.0f).setDuration(3000).start();
                ObjectAnimator.ofFloat(mImg, "translationX", 0, 55).setDuration(3000).start();
                ObjectAnimator.ofFloat(mImg, "rotation", 0.0f, 360f, 180f).setDuration(3000).start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn1:
                //alpha
               break;
           
        }
    }
}
