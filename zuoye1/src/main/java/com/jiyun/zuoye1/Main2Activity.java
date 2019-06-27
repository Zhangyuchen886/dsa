package com.jiyun.zuoye1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg2;
    /**
     * 跳过
     */
    private Button mBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mImg2 = (ImageView) findViewById(R.id.img2);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        getAnimation(mImg2);
    }

    private void getAnimation(View view) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mImg2, "alpha", 1, 0, 1);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mImg2, "rotation", 0,360);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mImg2, "scaleX", 1,2,1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mImg2, "scaleY", 1,2,1);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mImg2, "translationX", 0,280,200);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mImg2, "translationY", 0,280,200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(4000);
        animatorSet.play(alpha).with(rotation).with(scaleX).with(scaleY).with(translationX).with(translationY);
        animatorSet.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn1:
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
                break;
        }
    }
}
