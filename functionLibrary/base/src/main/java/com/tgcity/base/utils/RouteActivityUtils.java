package com.tgcity.base.utils;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.interfaces.RouteActivtyArrivalBack;

/**
 * @author TGCity
 * @date 2019/8/28
 * @describe jump activity by route
 */
public class RouteActivityUtils {

    /**
     * 跳转main模块主页
     */
    public static void toJumpMainModeIndexActivity(Context context, final RouteActivtyArrivalBack arrivalBack) {
        final String path = RouteConstant.MainMode.MAIN_FRAGMENT;
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
     * 跳转登录模块主页
     */
    public static void toJumpLoginModeIndexActivity(Context context, final RouteActivtyArrivalBack arrivalBack) {
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

}
