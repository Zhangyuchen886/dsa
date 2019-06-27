package com.jiyun.zhangyuchenqi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiyun.zhangyuchenqi.R;

public class ViewpagerFragment extends Fragment {
    private View view;
    private ImageView mShowviewpagerimg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showviewpageritem, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mShowviewpagerimg = (ImageView) view.findViewById(R.id.showviewpagerimg);
        FragmentActivity activity = getActivity();
        Intent intent = activity.getIntent();
        String path = intent.getStringExtra("path");
        Glide.with(getActivity()).load(path).into(mShowviewpagerimg);
    }
}
