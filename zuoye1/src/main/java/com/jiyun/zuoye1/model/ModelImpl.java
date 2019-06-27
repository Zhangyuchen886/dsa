package com.jiyun.zuoye1.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jiyun.zuoye1.ApiService;
import com.jiyun.zuoye1.bean.FuliBean;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImpl implements IModel {
    private static final String TAG = "ModelImpl";
    @Override
    public void getFuliUrl(final CallBacks callBacks) {
        new Retrofit.Builder()
                .baseUrl(ApiService.FuliURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getFuliBeanurl()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result=response.body().string();
                            Log.d(TAG, "onResponse: "+result);
                            FuliBean fuliBean = new Gson().fromJson(result, FuliBean.class);
                            List<FuliBean.ResultsBean> results = fuliBean.getResults();
                                callBacks.updateFaled(results);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                        callBacks.updateError(t.getMessage());
                    }
                });

    }
}
