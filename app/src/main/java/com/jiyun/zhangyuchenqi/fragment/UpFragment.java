package com.jiyun.zhangyuchenqi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.zhangyuchenqi.R;

public class UpFragment extends Fragment {
    private View view;
    /**
     * 图册名称
     */
    private TextView mUpname;
    /**
     * 收藏时间
     */
    private TextView mUptime;
    private ImageView mUpimg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upitem, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mUpname = (TextView) view.findViewById(R.id.upname);
        mUptime = (TextView) view.findViewById(R.id.uptime);
        mUpimg = (ImageView) view.findViewById(R.id.upimg);
      /*  Intent intent = getActivity().getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        String id = intent.getStringExtra("id");
        mUpname.setText(title);
        mUptime.setText(id);
        Glide.with(getActivity()).load(url).into(mUpimg);*/

    }
}
