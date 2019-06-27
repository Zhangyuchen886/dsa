package com.jiyun.zuoye1.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiyun.zuoye1.ApiService;
import com.jiyun.zuoye1.FuliActivity;
import com.jiyun.zuoye1.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment3 extends Fragment implements View.OnClickListener{

    private View view;
    /**
     * okhttp上传文件
     */
    private Button mFrag3BenOk;
    /**
     * Retrofit上传文件
     */
    private Button mFrag3BenRetrofit;
    private TextView mFrag3Tv;
    /**
     * 广播测试
     */
    private Button mFrag3BenBorader;
    private static String ACTIONURL = "com.example.zy_kaoti_two.aa";
    private Fragment3.bordservice bordservice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentthree, null);
        bordservice = new bordservice();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTIONURL);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(bordservice, intentFilter);
        initView(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(bordservice);
    }

    private void initView(View view) {
        mFrag3BenOk = (Button) view.findViewById(R.id.frag3_ben_ok);
        mFrag3BenOk.setOnClickListener(this);
        mFrag3BenRetrofit = (Button) view.findViewById(R.id.frag3_ben_retrofit);
        mFrag3BenRetrofit.setOnClickListener(this);
        mFrag3Tv = (TextView) view.findViewById(R.id.frag3_tv);

        mFrag3BenBorader = (Button) view.findViewById(R.id.frag3_ben_borader);
        mFrag3BenBorader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.frag3_ben_ok:
                initOKhttp();
                break;
            case R.id.frag3_ben_retrofit:
                initRetrofit();
                break;
            case R.id.frag3_ben_borader:
                Intent intent = new Intent();
                intent.setAction(ACTIONURL);
                intent.putExtra("data", "我是广播");
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                break;
        }
    }

    private static final String TAG = "FragmentThree";
    private void initRetrofit() {
        String filepath = Environment.getExternalStorageDirectory()+File.separator+ "test.jpg";
        File file = new File(filepath);
        if (!file.exists()) {
            Log.d(TAG, "initRetrofit: 不存在");
            return;
        }
        //"key"
        RequestBody params = RequestBody.create(MediaType.parse("text/plain"), "1811aaa");
        //file
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part mulitipart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.filepathURL)
                .build()
                .create(ApiService.class)
                .getfilePath(params, mulitipart)
                .enqueue(new retrofit2.Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        try {
                            String result=response.body().string();
                            Log.d(TAG, "onResponse: "+result);
                            mFrag3Tv.setText(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });


    }



    private void initOKhttp() {
        String filepath = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
        File file = new File(filepath);
        if (!file.exists()) {
            Log.d(TAG, "initOKhttp: 不存在");
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "1811")
                .addFormDataPart("file", file.getName(), requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(multipartBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: -----------" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.d(TAG, "onResponse: " + result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mFrag3Tv.setText(result);
                    }
                });

            }
        });
    }

    public class bordservice extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("data");
            Log.d(TAG, "onReceive: " + data);
            Intent intent1 = new Intent(getActivity(), FuliActivity.class);
            intent1.putExtra("data", data);
            PendingIntent activity = PendingIntent.getActivity(getActivity(), 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager system = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            Notification build = new NotificationCompat.Builder(getActivity())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("标题")
                    .setContentText(data)
                    .setContentIntent(activity)
                    .build();
            system.notify(1, build);
        }
    }
}
