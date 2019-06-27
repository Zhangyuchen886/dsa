package com.jiyun.zuoye1.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiyun.zuoye1.FuliActivity;
import com.jiyun.zuoye1.R;
import com.jiyun.zuoye1.adapter.Adapterfragtworecyc;
import com.jiyun.zuoye1.bean.FuliBean;
import com.jiyun.zuoye1.iview.IView;
import com.jiyun.zuoye1.presenter.PresenterImpl;

import java.util.List;

public class Fragment2 extends Fragment implements IView, View.OnClickListener {
    private View view;
    private RecyclerView mFrag2Recyc;
    private PresenterImpl presenter;
    private Adapterfragtworecyc adapterfragtworecyc;
    private PopupWindow pw;
    private int index;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttwo, null);
        initView(view);
        initPresenter();
        return view;

    }

    private void initPresenter() {
        presenter = new PresenterImpl((IView) this);
        presenter.getFuliList();
    }

    private void initView(View view) {
        mFrag2Recyc = (RecyclerView) view.findViewById(R.id.frag2_recyc);
        mFrag2Recyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFrag2Recyc.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        adapterfragtworecyc = new Adapterfragtworecyc(getActivity());
        mFrag2Recyc.setAdapter(adapterfragtworecyc);

        View view1 = View.inflate(getActivity(), R.layout.pop_item, null);
        TextView remove = view1.findViewById(R.id.pop_remove);
        remove.setOnClickListener(this);
        pw = new PopupWindow(view1, 200, 400);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setAnimationStyle(R.style.pwAnimation);

        adapterfragtworecyc.setOn(new Adapterfragtworecyc.onItemLongClick() {
            @Override
            public void onItemLong(int i) {
                List<FuliBean.ResultsBean> resultsBeans = adapterfragtworecyc.getResultsBeans();
                FuliBean.ResultsBean resultsBean = resultsBeans.get(i);
                Intent intent = new Intent(getActivity(), FuliActivity.class);
                intent.putExtra("resultsBean",resultsBean);
                startActivity(intent);
            }

            @Override
            public void onItem(int i) {
                pw.showAtLocation(mFrag2Recyc, Gravity.CENTER,0,0);
                index=i;

            }
        });
    }

    private static final String TAG = "Fragmenttwo";
    @Override
    public void updateUIFeldel(List<FuliBean.ResultsBean> results) {
        adapterfragtworecyc.initData(results);
    }

    @Override
    public void updateUIError(String error) {
    }

    @Override
    public void onClick(View v) {
        adapterfragtworecyc.remove(index);
        pw.dismiss();
    }
}
