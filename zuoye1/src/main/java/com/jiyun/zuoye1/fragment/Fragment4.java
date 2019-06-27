package com.jiyun.zuoye1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.zuoye1.R;
import com.jiyun.zuoye1.SqlUtils;
import com.jiyun.zuoye1.adapter.Adapterfragfourrecyc;
import com.jiyun.zuoye1.bean.AndroidBean;

import java.util.List;

public class Fragment4 extends Fragment {

    private View view;
    private RecyclerView mFrag4Recyc;
    private Adapterfragfourrecyc adapterfragfourrecyc;


    public RecyclerView getmFrag4Recyc() {
        return mFrag4Recyc;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfour, null);

        initView(view);
        return view;
    }


    private void initView(View view) {
        mFrag4Recyc = (RecyclerView) view.findViewById(R.id.frag4_recyc);
        mFrag4Recyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFrag4Recyc.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapterfragfourrecyc = new Adapterfragfourrecyc(getActivity());

        List<AndroidBean> androidBeans = SqlUtils.queryAll();
        adapterfragfourrecyc.initData(androidBeans);
        mFrag4Recyc.setAdapter(adapterfragfourrecyc);


        adapterfragfourrecyc.setOn(new Adapterfragfourrecyc.onItemLongClick() {
            @Override
            public void onItemLong(AndroidBean androidBean,int i) {
                adapterfragfourrecyc.remove(i);
                SqlUtils.delete(androidBean);
                adapterfragfourrecyc.notifyDataSetChanged();
            }
        });
    }

}
