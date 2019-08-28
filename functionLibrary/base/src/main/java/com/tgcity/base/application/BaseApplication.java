package com.tgcity.base.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.LogUtils;
import com.tgcity.base.utils.SharedPreferencesUtils;

import java.util.List;

import me.jessyan.autosize.AutoSizeConfig;

/**
 * @author TGCity
 * 基础的Application
 */
public class BaseApplication extends Application {
    /**
     * 静态单例
     */
    private static BaseApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instances == null) {
            instances = this;
        }
        //设置LogUtils开关
        LogUtils.logSwitch = BaseConstant.Power.IS_LOG_UTIL_OPEN;
        //判断是否为主进程。防止重复初始化
        String mainProcessName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == Process.myPid()) {
                    mainProcessName = procInfo.processName;
                }
            }
        }
        if (getPackageName().equals(mainProcessName)){
            //初始化屏幕适配
            AutoSizeConfig.getInstance()
                    .setCustomFragment(true)
                    .setLog(BaseConstant.Power.IS_AUTO_SIZE_LOG_SHOW)
                    .getUnitsManager()
                    .setSupportDP(true)
                    .setSupportSP(true);
            //设置路由
            if (BaseConstant.Power.IS_ROUTER_LOG_SHOW){
                ARouter.openLog();
                ARouter.openDebug();
            }
            ARouter.init(instances);
            initMain();
        }

    }

    /**
     * 初始化main进程
     */
    private void initMain() {
        BaseConstant.sharedPreferencesUtils = new SharedPreferencesUtils(getInstances());
    }

    public static BaseApplication getInstances() {
        if (instances == null) {
            instances = new BaseApplication();
        }
        return instances;
    }

    /**
     * 此方法在真机上未触发，待研究替代方法
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtils.e("onTerminate");
        //关闭路由
        ARouter.getInstance().destroy();
        //清除 sharedPreferencesUtils
        BaseConstant.sharedPreferencesUtils = null;
    }

}
