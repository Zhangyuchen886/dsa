package com.jiyun.zuoye1.presenter;

import com.jiyun.zuoye1.bean.FuliBean;
import com.jiyun.zuoye1.iview.IView;
import com.jiyun.zuoye1.model.CallBacks;
import com.jiyun.zuoye1.model.ModelImpl;

import java.util.List;


public class PresenterImpl implements IPresenter {

    private final ModelImpl model;
    private IView iView;
    public PresenterImpl( IView iView) {
        model = new ModelImpl();
        this.iView=iView;
    }

    @Override
    public void getFuliList() {
        model.getFuliUrl(new CallBacks() {
            @Override
            public void updateFaled(List<FuliBean.ResultsBean> result) {
                iView.updateUIFeldel(result);
            }

            @Override
            public void updateError(String error) {
                iView.updateUIError(error);
            }
        });
    }
}
