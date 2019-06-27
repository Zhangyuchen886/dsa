package com.jiyun.zuoye1;

import android.app.Application;

import com.jiyun.zuoye1.dao.DaoMaster;
import com.jiyun.zuoye1.dao.DaoSession;

public class MyApplication extends Application {
    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "android.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

         daoSession = daoMaster.newSession();
    }
}
