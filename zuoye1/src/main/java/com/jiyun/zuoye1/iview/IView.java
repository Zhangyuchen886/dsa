package com.jiyun.zuoye1.iview;

import com.jiyun.zuoye1.bean.FuliBean;

import java.util.List;

public interface IView {
    void updateUIFeldel(List<FuliBean.ResultsBean> results);
    void updateUIError(String error);
}
