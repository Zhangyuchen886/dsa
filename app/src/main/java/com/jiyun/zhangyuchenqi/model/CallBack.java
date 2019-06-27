package com.jiyun.zhangyuchenqi.model;

import com.jiyun.zhangyuchenqi.bean.Infos;

import java.util.List;

public interface CallBack {
    void getMessage(List<Infos.RecentBean> msg);
    void getError(String e);
}
