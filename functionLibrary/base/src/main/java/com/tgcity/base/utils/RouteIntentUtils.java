package com.tgcity.base.utils;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.interfaces.RouteNavigationCallBack;

/**
 * @author TGCity
 * @date 2019/8/28
 * @describe jump page by route
 */
public class RouteIntentUtils {

    /**
     * jump launcher mode index activity
     */
    public static void toJumpLauncherModeIndexActivity(Context context, final RouteNavigationCallBack callBack) {
        final String path = RouteConstant.LaunchMode.MAIN_FRAGMENT;
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
    public static void toJumpMainModeIndexFragment(Context context, final RouteNavigationCallBack callBack) {
        final String path = RouteConstant.MainMode.MAIN_FRAGMENT;
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
    public static void toJumpLoginModeIndexActivity(Context context, final RouteNavigationCallBack arrivalBack) {
        final String path = RouteConstant.LoginMode.MAIN_FRAGMENT;
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
    public static void toJumpWebModeIndexActivity(Context context, final RouteNavigationCallBack arrivalBack) {
        final String path = RouteConstant.WebMode.MAIN_FRAGMENT;
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
