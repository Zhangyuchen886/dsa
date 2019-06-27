package com.jiyun.zuoye1;

import android.util.Log;

import com.jiyun.zuoye1.bean.AndroidBean;
import com.jiyun.zuoye1.dao.AndroidBeanDao;
import com.jiyun.zuoye1.dao.DaoSession;

import java.util.List;

public class SqlUtils {
    public static DaoSession daoSession = MyApplication.getDaoSession();
    private static final String TAG = "SqlUtils";
    public static  void insert(AndroidBean androidBean){
        List<AndroidBean> androidBeans = queryItem(androidBean.getTitle());
        if (androidBeans.size()==0){
            daoSession.insert(androidBean);
        }else {
            Log.d(TAG, "insert: 已存在");
        }
    }
    public static  void delete(AndroidBean androidBean){
        List<AndroidBean> androidBeans = queryItem(androidBean.getTitle());
        if (androidBeans.size()>0&&androidBeans!=null){
            daoSession.delete(androidBean);
        }
    }
    public static List<AndroidBean> queryItem(String title){
        List<AndroidBean> list = daoSession.queryBuilder(AndroidBean.class)
                .where(AndroidBeanDao.Properties.Title.eq(title))
                .build()
                .list();
        return list;
    }
    public static List<AndroidBean> queryAll(){
        return daoSession.loadAll(AndroidBean.class);
    }
}
