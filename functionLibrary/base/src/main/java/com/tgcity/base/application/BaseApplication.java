package com.tgcity.base.application;

import android.app.Application;

import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.LogUtils;

import me.jessyan.autosize.AutoSizeConfig;

/**
 * 基础的Application
 */
public class BaseApplication extends Application {
    //静态单例
    private static BaseApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instances == null) {
            instances = this;
        }
        //设置LogUtils开关
        LogUtils.logSwitch = BaseConstant.Power.isLogUtilOpen;
        //初始化屏幕适配
        AutoSizeConfig.getInstance()
                .setCustomFragment(true)
                .setLog(BaseConstant.Power.isAutoSizeLogShow)
                .getUnitsManager()
                .setSupportDP(true)
                .setSupportSP(true);
    }

    public static BaseApplication getInstances() {
        if (instances == null) {
            instances = new BaseApplication();
        }
        return instances;
    }

}
