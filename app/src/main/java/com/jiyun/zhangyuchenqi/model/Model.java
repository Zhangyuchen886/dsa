package com.jiyun.zhangyuchenqi.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jiyun.zhangyuchenqi.ApiService;
import com.jiyun.zhangyuchenqi.bean.Infos;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements IModel {
    private static final String TAG = "Model";

    @Override
    public void getTask(final CallBack callBack) {
        //处理逻辑代码
        new Retrofit.Builder()
                .baseUrl(ApiService.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getDatas()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result = response.body().string();
                            Log.d(TAG, "onResponse: " + result);
                            Infos infos = new Gson().fromJson(result, Infos.class);
                            List<Infos.RecentBean> datas = infos.getRecent();
                            callBack.getMessage(datas);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        callBack.getError(t.getMessage());
                    }
                });
    }
}
