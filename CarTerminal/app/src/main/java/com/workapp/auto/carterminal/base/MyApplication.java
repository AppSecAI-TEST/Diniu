package com.workapp.auto.carterminal.base;


import android.support.multidex.MultiDexApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * 应用程序实体
 * Created by Administrator on 2016/9/15.
 */
public class MyApplication extends MultiDexApplication {
    private static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static MyApplication getInstance() {
        return app;
    }
}
