package com.tgcity.mode.launch.guide;

import android.os.Handler;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.utils.LogUtils;
import com.tgcity.base.utils.SharedPreferencesUtils;
import com.tgcity.mode.launch.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * launch模块--启动界面
 */
@Route(path = RouteConstant.LaunchMode.MAIN_FRAGMENT)
public class GuideActivity extends BaseCommonActivity {
    //Gif动画
    private GifImageView gifView;
    //缓存工具类
    private SharedPreferencesUtils spUtils;

    @Override
    public int getViewLayout() {
        return R.layout.launch_activity_guide;
    }

    @Override
    public void initView() {
        gifView = findViewById(R.id.gifView);
        gifView.setImageResource(R.drawable.guide_bg);
        spUtils = new SharedPreferencesUtils(getContext());
    }

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //1秒后动画结束后执行相关操作
        Handler handler = new Handler();
        //界面跳转
        handler.postDelayed(this::toJumpActivity, 1000);
    }

    /**
     * 界面跳转,判断是跳转登录还是跳转主页
     */
    private void toJumpActivity() {
//        if (spUtils.getBoolean(BaseConstant.SP.CACHE_IS_LOGIN)) {
        if (true) {
            toJumpMainMode();
        } else {
            toJumpLoginMode();
        }
    }

    /**
     * 跳转main模块主页
     */
    private void toJumpMainMode() {
        ARouter.getInstance().build(RouteConstant.MainMode.MAIN_FRAGMENT).navigation(getContext(), new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                LogUtils.e("onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                LogUtils.e("onLost");
            }

            @Override
            public void onArrival(Postcard postcard) {
                LogUtils.e("onArrival");
                finish();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                LogUtils.e("onInterrupt");
            }
        });
    }

    /**
     * 跳转登录模块
     */
    private void toJumpLoginMode() {

    }

}
