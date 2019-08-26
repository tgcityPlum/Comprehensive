package com.tgcity.base.constant;

/**
 * 基础的静态常量
 */
public class BaseConstant {

    /**
     * 开关类
     */
    public static class Power{
        //app编译终极开关：是否处于debug模式 true-debug  false-release
        private static final boolean isAppDebug = true;
        //logUtil的日志开关：是否开启日志  true-开启  false-关闭
        public static final boolean isLogUtilOpen = isAppDebug && true;
        //Activity的启动时间开关：是否显示启动时间  true-显示  false-不显示
        public static final boolean isLauncherTimeActivityLogShow = isAppDebug && true;
        //Activity的事件逻辑开关：是否显示事件开关  true-显示  false-不显示
        public static final boolean isBaseEventLogicActivityLogShow = isAppDebug && true;
        //Activity的绑定view层开关：是否显示绑定开关  true-显示  false-不显示
        public static final boolean isBaseBindViewActivityLogShow = isAppDebug && true;
        //监听系统内存状况的开关：是否显示内存状况  true-显示  false-不显示
        public static final boolean isBaseMemoryActivityLogShow = isAppDebug && true;
        //Activity的沉浸式状态栏开关：是否显示沉浸式状态栏开关  true-显示  false-不显示
        public static final boolean isBaseImmersionBarActivityLogShow = isAppDebug && true;
        //尺寸适配日志开关：是否显示尺寸适配日志开关  true-显示  false-不显示
        public static final boolean isAutoSizeLogShow = isAppDebug && true;
    }

    //服务器域名
    public class Service {
        public final static long SERVICE_TRY_WAIT = 10 * 1000;//出错重试等待时间（暂时定为10秒）
        public final static long SERVICE_TRY_INTERVAL = 1 * 1000;//出错重试时的间隔时间（暂时定为1秒）
    }
}