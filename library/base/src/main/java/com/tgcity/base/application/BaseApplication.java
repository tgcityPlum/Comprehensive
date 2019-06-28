package com.tgcity.base.application;

import android.app.Application;

import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.LogUtils;

/**
 * 基础的Application
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.logSwitch = BaseConstant.Power.isLogUtilOpen;
    }
}
