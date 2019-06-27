package com.jiyun.zhangyuchenqi.presenter;

import com.jiyun.zhangyuchenqi.bean.Infos;
import com.jiyun.zhangyuchenqi.model.CallBack;
import com.jiyun.zhangyuchenqi.model.Model;
import com.jiyun.zhangyuchenqi.view.IView;

import java.util.List;

public class Presenter implements IPresenter {
    private IView iView;
    private final Model model;

    public Presenter(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    @Override
    public void getInfo() {
        model.getTask(new CallBack() {
            @Override
            public void getMessage(List<Infos.RecentBean> msg) {
                iView.upDateMessage(msg);
            }

            @Override
            public void getError(String e) {
                iView.upDateError(e);
            }
        });
    }
}
