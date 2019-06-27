package com.jiyun.zuoye1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiyun.zuoye1.ApiService;
import com.jiyun.zuoye1.R;
import com.jiyun.zuoye1.SqlUtils;
import com.jiyun.zuoye1.adapter.AdapterRecyc;
import com.jiyun.zuoye1.adapter.MyAdapter;
import com.jiyun.zuoye1.bean.AndroidBean;
import com.jiyun.zuoye1.bean.BannerBean;
import com.jiyun.zuoye1.bean.BannerInfos;
import com.jiyun.zuoye1.bean.Infos;
import com.jiyun.zuoye1.bean.ProjectBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment {
    private View view;
    private Banner mMyBanner;
    private RecyclerView mMyRecyc;
    private SmartRefreshLayout mSmart;
    private int page = 1;
    private AdapterRecyc adapterRecyc;
    private int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, null);
        initView(view);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser == true) {
            initBanner();
            initData();
        }
    }

    private void initData() {
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.prijectURl)
                .build()
                .create(ApiService.class)
                .getProjecturl(page)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result = response.body().string();
                            Log.d(TAG, "onResponse: " + result);
                            ProjectBean projectBean = new Gson().fromJson(result, ProjectBean.class);
                            List<ProjectBean.DataBean.DatasBean> datas = projectBean.getData().getDatas();
                            if (page == 1) {
                                adapterRecyc.initData(datas);
                                mSmart.finishRefresh();
                            } else {
                                adapterRecyc.loadMoreData(datas);
                                mSmart.finishLoadMore();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    private static final String TAG = "Fragmentone";

    private void initBanner() {
        new Retrofit.Builder()
                .baseUrl(ApiService.bannerURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getBannerurl()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result = response.body().string();
                            Log.d(TAG, "onResponse: " + result);
                            BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);
                            List<BannerBean.DataBean> data = bannerBean.getData();
                            mMyBanner.setImages(data).setImageLoader(new Images()).start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    private void initView(View view) {
        mMyBanner = (Banner) view.findViewById(R.id.myBanner);
        mMyRecyc = (RecyclerView) view.findViewById(R.id.myRecyc);
        mSmart = (SmartRefreshLayout) view.findViewById(R.id.smart);
        mMyRecyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRecyc.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapterRecyc = new AdapterRecyc(getActivity());
        mMyRecyc.setAdapter(adapterRecyc);
        registerForContextMenu(mMyRecyc);
        mSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
            }
        });
        mSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }
        });
        adapterRecyc.setOn(new AdapterRecyc.onItemLong() {
            @Override
            public void onItem(int i) {
                index = i;
            }
        });
    }

    class Images extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            BannerBean.DataBean bean = (BannerBean.DataBean) path;
            Glide.with(getActivity()).load(bean.getImagePath()).into(imageView);
        }
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "删除");
        menu.add(1, 2, 2, "收藏");
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                adapterRecyc.remove(index);
                adapterRecyc.notifyDataSetChanged();
                break;
            case 2:
                List<ProjectBean.DataBean.DatasBean> datasBeans = adapterRecyc.getDatasBeans();
                ProjectBean.DataBean.DatasBean datasBean = datasBeans.get(index);
                AndroidBean androidBean = new AndroidBean();
                androidBean.setEnvelopePic(datasBean.getEnvelopePic());
                androidBean.setTitle(datasBean.getTitle());
                SqlUtils.insert(androidBean);
                Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}