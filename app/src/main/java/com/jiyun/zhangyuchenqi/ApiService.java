package com.jiyun.zhangyuchenqi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    //http://news-at.zhihu.com/api/4/news/hot
    String Url="http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Call<ResponseBody> getDatas();
}
