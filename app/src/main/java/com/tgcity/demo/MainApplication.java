package com.tgcity.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
