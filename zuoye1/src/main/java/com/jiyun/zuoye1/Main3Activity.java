package com.jiyun.zuoye1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jiyun.zuoye1.adapter.VpAdapter;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity{

    private ViewPager mVp;
    /**
     * 跳过
     */
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initVp();
    }

    private void initVp() {
        ArrayList<View> list = new ArrayList<>();
        View view1 = View.inflate(this, R.layout.vp1, null);
        View view2 = View.inflate(this, R.layout.vp2, null);
        View view3 = View.inflate(this, R.layout.vp3, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        VpAdapter vpAdapter = new VpAdapter(list);
        mVp.setAdapter(vpAdapter);
        btn = view3.findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, HomeActivity.class));
            }
        });
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }


}
