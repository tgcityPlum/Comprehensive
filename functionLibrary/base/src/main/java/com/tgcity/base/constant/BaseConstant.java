package com.tgcity.base.constant;

import com.tgcity.base.utils.SharedPreferencesUtils;

/**
 * @author TGCity
 * 基础的静态常量
 */
public class BaseConstant {

    /**
     * 开关类
     */
    public static class Power{
        //app编译终极开关：是否处于debug模式 true-debug  false-release
        public static final boolean IS_APP_DEBUG = true;
        //logUtil的日志开关：是否开启日志  true-开启  false-关闭
        public static final boolean IS_LOG_UTIL_OPEN = IS_APP_DEBUG && true;
        //Activity的启动时间开关：是否显示启动时间  true-显示  false-不显示
        public static final boolean IS_LAUNCHER_TIME_ACTIVITY_LOG_SHOW = IS_APP_DEBUG && true;
        //Activity的事件逻辑开关：是否显示事件开关  true-显示  false-不显示
        public static final boolean IS_BASE_EVENT_LOGIN_ACTIVITY_LOG_SHOW = IS_APP_DEBUG && true;
        //Activity的绑定view层开关：是否显示绑定开关  true-显示  false-不显示
        public static final boolean IS_BASE_BIND_VIEW_ACTIVITY_LOG_SHOW = IS_APP_DEBUG && true;
        //监听系统内存状况的开关：是否显示内存状况  true-显示  false-不显示
        public static final boolean IS_BASE_MEMORY_ACTIVITY_LOG_SHOW = IS_APP_DEBUG && true;
        //Activity的沉浸式状态栏开关：是否显示沉浸式状态栏开关  true-显示  false-不显示
        public static final boolean IS_BASE_IMMERSION_BAR_ACTIVITY_LOG_SHOW = IS_APP_DEBUG && true;
        //尺寸适配日志开关：是否显示尺寸适配日志开关  true-显示  false-不显示
        public static final boolean IS_AUTO_SIZE_LOG_SHOW = IS_APP_DEBUG && true;
        //路由日志开关：是否显示日志开关  true-显示  false-不显示
        public static final boolean IS_ROUTER_LOG_SHOW = IS_APP_DEBUG && true;
    }

    /**
     *   服务器域名
     */
    public class Service {
        //出错重试等待时间（暂时定为10秒）
        public final static long SERVICE_TRY_WAIT = 10 * 1000;
        //出错重试时的间隔时间（暂时定为1秒）
        public final static long SERVICE_TRY_INTERVAL = 1 * 1000;
    }

    public static SharedPreferencesUtils sharedPreferencesUtils;

    /**
     * SP
     */
    public class SP {
        public static final String CONFIG = "comprehensive_cfg";//配置文件名

        // net mode
        public static final String CACHE_COLLEGE = "Cache_College";//缓存院校
        public static final String CACHE_MAJOR = "Cache_Major";//缓存专业
        public static final String CACHE_PCL = "Cache_PCL";//缓存省控线
        public static final String CACHE_OTHER = "Cache_Other";//缓存其他

        //launch mode
        public static final String CACHE_IS_LOGIN = "Cache_Is_Login";//是否已经登录
    }

    /**
     * 用来区分item类型
     */
    public class ItemType {
        public static final int LEVEL_0 = 0;
        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;
    }
}
