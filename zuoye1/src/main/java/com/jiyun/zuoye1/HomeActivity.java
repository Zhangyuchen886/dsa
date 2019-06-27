package com.jiyun.zuoye1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.jiyun.zuoye1.adapter.MyVpAdapter;
import com.jiyun.zuoye1.fragment.Fragment1;
import com.jiyun.zuoye1.fragment.Fragment2;
import com.jiyun.zuoye1.fragment.Fragment3;
import com.jiyun.zuoye1.fragment.Fragment4;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    //张雨晨1811a
    private Toolbar mTool;
    private ViewPager mVp1;
    private TabLayout mTab;
    private LinearLayout mMyll;
    private NavigationView mNv;
    private DrawerLayout mMydraw;
    private ArrayList<String> list2;
    private Fragment4 f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initToolBar();
        initDraw();

        initVp();
    }

    private void initVp() {
        ArrayList<Fragment> list = new ArrayList<>();

        Fragment1 f1 = new Fragment1();
        Fragment2 f2 = new Fragment2();
        Fragment3 f3 = new Fragment3();
        f4 = new Fragment4();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        MyVpAdapter myVpAdapter = new MyVpAdapter(getSupportFragmentManager(), list);
        mVp1.setAdapter(myVpAdapter);
        mTab.addTab(mTab.newTab());
        mTab.setupWithViewPager(mVp1);


    }


    private void initDraw() {
        mMydraw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                mMyll.setX(mNv.getWidth() * v);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    private void initToolBar() {
        mTool.setTitle("");
        setSupportActionBar(mTool);
        mTool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mMydraw, R.string.app_name, R.string.app_name);
        toggle.syncState();
        mMydraw.addDrawerListener(toggle);
    }

    private void initView() {
        mTool = (Toolbar) findViewById(R.id.tool);
        mVp1 = (ViewPager) findViewById(R.id.vp1);
        mTab = (TabLayout) findViewById(R.id.tab);
        mMyll = (LinearLayout) findViewById(R.id.myll);
        mNv = (NavigationView) findViewById(R.id.nv);
        mMydraw = (DrawerLayout) findViewById(R.id.mydraw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "线性布局");
        menu.add(1, 2, 1, "网格布局");
        menu.add(1, 3, 1, "瀑布流");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (f4.getmFrag4Recyc() != null) {
            switch (item.getItemId()) {
                case 1:

                    f4.getmFrag4Recyc().setLayoutManager(new LinearLayoutManager(this));
                    break;
                case 2:
                    f4.getmFrag4Recyc().setLayoutManager(new GridLayoutManager(this, 3));
                    break;
                case 3:
                    f4.getmFrag4Recyc().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
