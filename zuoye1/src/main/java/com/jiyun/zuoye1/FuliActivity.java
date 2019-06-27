package com.jiyun.zuoye1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiyun.zuoye1.bean.FuliBean;

public class FuliActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mFuliTv;
    /**
     * zy_kaoti_two
     */
    private Button mFuliBtn;
    /**
     * 广播
     */
    private Button mFuliBtnGuangbo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli);
        initView();
    }

    private void initView() {
        mFuliTv = (TextView) findViewById(R.id.fuli_tv);

        mFuliBtn = (Button) findViewById(R.id.fuli_btn);
        mFuliBtn.setOnClickListener(this);
        mFuliBtnGuangbo = (Button) findViewById(R.id.fuli_btn_guangbo);
        mFuliBtnGuangbo.setOnClickListener(this);
        intent = getIntent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fuli_btn:

                FuliBean.ResultsBean resultsBean = (FuliBean.ResultsBean) intent.getSerializableExtra("resultsBean");
                if (resultsBean!=null) {
                    mFuliTv.setText(resultsBean.get_id());
                }

                break;
            case R.id.fuli_btn_guangbo:
                String data = intent.getStringExtra("data");
                mFuliTv.setText(data);
                break;
        }
    }
}
