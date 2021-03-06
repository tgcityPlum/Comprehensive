package com.tgcity.mode.arouter.utils;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.utils.LogUtils;
import com.tgcity.mode.arouter.constant.RouteConstant;
import com.tgcity.mode.arouter.interfaces.RouteNavigationCallBack;

/**
 * @author TGCity
 * @date 2019/8/28
 * @describe jump page by route
 */
public class RouteIntentUtils {

    /**
     * jump launcher mode index activity
     */
    public static void onLauncherModeIndexActivity(Context context, final RouteNavigationCallBack callBack) {
        final String path = RouteConstant.LaunchMode.LAUNCH_ACTIVITY_GUIDE;
        ARouter.getInstance().build(path).navigation(context, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                LogUtils.e(path + " onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                LogUtils.e(path + " onLost");
                callBack.onLostBack();
            }

            @Override
            public void onArrival(Postcard postcard) {
                LogUtils.e(path + " onArrival");
                callBack.onArrivalBack();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                LogUtils.e(path + " onInterrupt");
            }
        });
    }

    /**
     * jump main mode index fragment
     */
    public static void onMainModeIndexActivity(Context context, final RouteNavigationCallBack callBack) {
        final String path = RouteConstant.MainMode.MAIN_ACTIVITY_CORE;
        ARouter.getInstance().build(path).navigation(context, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                LogUtils.e(path + " onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                LogUtils.e(path + " onLost");
                callBack.onLostBack();
            }

            @Override
            public void onArrival(Postcard postcard) {
                LogUtils.e(path + " onArrival");
                callBack.onArrivalBack();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                LogUtils.e(path + " onInterrupt");
            }
        });
    }

    /**
     * jump login mode index activity
     */
    public static void onLoginModeIndexActivity(Context context, final RouteNavigationCallBack arrivalBack) {
        final String path = RouteConstant.LoginMode.LOGIN_ACTIVITY_LOGIN;
        ARouter.getInstance().build(path).navigation(context, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                LogUtils.e(path + " onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                LogUtils.e(path + " onLost");
            }

            @Override
            public void onArrival(Postcard postcard) {
                LogUtils.e(path + " onArrival");
                arrivalBack.onArrivalBack();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                LogUtils.e(path + " onInterrupt");
            }
        });
    }

    /**
     * jump web mode index activity
     */
    public static void onWebModeIndexActivity(Context context, final RouteNavigationCallBack arrivalBack) {
        final String path = RouteConstant.WebMode.WEB_FRAGMENT;
        ARouter.getInstance().build(path).navigation(context, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                LogUtils.e(path + " onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                LogUtils.e(path + " onLost");
            }

            @Override
            public void onArrival(Postcard postcard) {
                LogUtils.e(path + " onArrival");
                arrivalBack.onArrivalBack();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                LogUtils.e(path + " onInterrupt");
            }
        });
    }

}
