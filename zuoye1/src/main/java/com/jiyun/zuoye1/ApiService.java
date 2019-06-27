package com.jiyun.zuoye1;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    String bannerURl="https://www.wanandroid.com/";
    @GET("banner/json")
    Call<ResponseBody> getBannerurl();
    //https://www.wanandroid.com/banner/json

    String prijectURl="https://www.wanandroid.com/";
    @GET("project/list/{pageIndex}/json?cid=294")
    Call<ResponseBody> getProjecturl(@Path("pageIndex")int pageIndex);
    //https://www.wanandroid.com/project/list/{pageIndex}/json?cid=294

    String FuliURl="http://gank.io/";
    @GET("api/data/福利/20/1")
    Call<ResponseBody>getFuliBeanurl();

    String filepathURL="http://yun918.cn/";
    @Multipart
    @POST("study/public/file_upload.php")
    Call<ResponseBody>getfilePath(@Part("key") RequestBody params, @Part MultipartBody.Part multipartbody);
}
