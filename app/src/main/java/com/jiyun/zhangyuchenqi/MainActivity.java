package com.jiyun.zhangyuchenqi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jiyun.zhangyuchenqi.adapter.ViewPagerAdapter;
import com.jiyun.zhangyuchenqi.fragment.ShowFragment;
import com.jiyun.zhangyuchenqi.fragment.UpFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * 图册
     */
    private TextView mTooltitle;
    private Toolbar mTool;
    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolBar();
        initVp();
        initTab();
    }

    private void initTab() {
        //设置Tablayout
        mTab.addTab(mTab.newTab());
        mTab.setupWithViewPager(mVp);

    }

    private void initVp() {

        //设置viewpager，创建集合
        ArrayList<Fragment> list = new ArrayList<>();
        //添加数据
        ShowFragment f1 = new ShowFragment();
        UpFragment f2 = new UpFragment();
        list.add(f1);
        list.add(f2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        //给ViewPager设置适配器
        mVp.setAdapter(viewPagerAdapter);
    }

    private void initToolBar() {
        //设置ToolBar
        mTool.setTitle("");
        setSupportActionBar(mTool);

    }

    private void initView() {
        mTooltitle = (TextView) findViewById(R.id.tooltitle);
        mTool = (Toolbar) findViewById(R.id.tool);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
    }
}
