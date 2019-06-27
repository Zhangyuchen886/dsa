package com.jiyun.zhangyuchenqi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jiyun.zhangyuchenqi.adapter.ViewPagerAdapter;
import com.jiyun.zhangyuchenqi.fragment.ViewpagerFragment;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {


    private ViewPager mShowviewp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {

        mShowviewp = (ViewPager) findViewById(R.id.showviewp);
        ArrayList<Fragment> list = new ArrayList<>();
        ViewpagerFragment f1 = new ViewpagerFragment();
        list.add(f1);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        mShowviewp.setAdapter(viewPagerAdapter);
    }
}
