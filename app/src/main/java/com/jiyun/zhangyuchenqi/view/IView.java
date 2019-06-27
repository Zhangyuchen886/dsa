package com.jiyun.zhangyuchenqi.view;

import com.jiyun.zhangyuchenqi.bean.Infos;

import java.util.List;

public interface IView {
    void upDateMessage(List<Infos.RecentBean> msg);
    void upDateError(String e);
}
