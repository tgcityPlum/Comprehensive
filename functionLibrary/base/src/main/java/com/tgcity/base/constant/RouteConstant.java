package com.tgcity.base.constant;

/**
 * @author TGCity
 * route constant
 */
public class RouteConstant {

    /**
     * launch mode
     */
    public class LaunchMode {
        //初始页
        public static final String LAUNCH_ACTIVITY_GUIDE = "/launch/guideActivity";
    }

    /**
     * main mode
     */
    public class MainMode {
        //初始页
        public static final String MAIN_ACTIVITY_CORE = "/main/MainCoreActivity";
        public static final String MAIN_ACTIVITY_DEMO2 = "/main/demo2Activity";
    }

    /**
     * Login mode
     */
    public class LoginMode {
        //初始页
        public static final String LOGIN_ACTIVITY_LOGIN = "/login/loginActivity";
    }

    /**
     * home mode
     */
    public static class HomeMode{
        //初始页
        public static final String HOME_FRAGMENT = "/home/mainFragment";
    }

    /**
     * news mode
     */
    public class NewsMode {
        //初始页
        public static final String NEWS_FRAGMENT = "/news/mainFragment";
    }

    /**
     * web mode
     */
    public class WebMode {
        //初始页
        public static final String WEB_FRAGMENT = "/web/commonActivity";
    }

}
