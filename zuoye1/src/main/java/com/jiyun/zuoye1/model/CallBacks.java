package com.jiyun.zuoye1.model;

import com.jiyun.zuoye1.bean.FuliBean;

import java.util.List;

public interface CallBacks {
    void updateFaled(List<FuliBean.ResultsBean> result);
    void updateError(String error);
}
