package com.jiyun.zhangyuchenqi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jiyun.zhangyuchenqi.MainActivity;
import com.jiyun.zhangyuchenqi.R;
import com.jiyun.zhangyuchenqi.ShowActivity;
import com.jiyun.zhangyuchenqi.adapter.RecycAdapter;
import com.jiyun.zhangyuchenqi.bean.Infos;
import com.jiyun.zhangyuchenqi.presenter.Presenter;
import com.jiyun.zhangyuchenqi.view.IView;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment implements IView {
    private View view;
    private RecyclerView mRecyc;
    private ArrayList<Infos.RecentBean> list;
    private RecycAdapter recycAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showitem, null);
        initView(view);
        initRecycViewData();
        initPresenter();
        return view;
    }

    private static final String TAG = "ShowFragment";
    private void initPresenter() {
        //调用p层方法
        Presenter presenter = new Presenter(this);
        presenter.getInfo();
    }

    private void initRecycViewData() {
        //设置布局管理区
        mRecyc.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyc.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //创建适配器
        list = new ArrayList<>();
        recycAdapter = new RecycAdapter(getActivity(), list);

        //绑定适配器
        mRecyc.setAdapter(recycAdapter);

        //长按监听方法
        recycAdapter.setShowLong(new RecycAdapter.itemshowLongClick() {
            @Override
            public void ItemLong(int position) {
                /*Intent intent = new Intent(getActivity(), UpFragment.class);

                String title = list.get(position).getTitle();
                String url = list.get(position).getThumbnail();
                int id = list.get(position).getNews_id();
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.putExtra("id",id);*/
                Toast.makeText(getActivity(), "跳转收藏", Toast.LENGTH_SHORT).show();

//                startActivity(intent);
            }
        });

        //点击监听方法
        recycAdapter.setShow(new RecycAdapter.ItemshowClick() {
            @Override
            public void ItemShow(View v, int position) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                String url = list.get(position).getThumbnail();
                Toast.makeText(getActivity(), "获取到数据，跳转", Toast.LENGTH_SHORT).show();
                intent.putExtra("path",url);
                startActivity(intent);
            }
        });

    }

    private void initView(View view) {
        mRecyc = (RecyclerView) view.findViewById(R.id.recyc);
    }

    @Override
    public void upDateMessage(List<Infos.RecentBean> msg) {
        //接受数据，传入集合，刷新适配器
        list.addAll(msg);
        recycAdapter.notifyDataSetChanged();
    }

    @Override
    public void upDateError(String e) {
        //日志打印错误信息
        Log.d(TAG, "upDateError: "+e);
    }
}
