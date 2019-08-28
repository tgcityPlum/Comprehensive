package com.tgcity.mode.launch.guide;

import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.utils.RouteActivityUtils;
import com.tgcity.base.utils.SharedPreferencesUtils;
import com.tgcity.mode.launch.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * @author TGCity
 * launch模块--启动界面
 */
@Route(path = RouteConstant.LaunchMode.MAIN_FRAGMENT)
public class GuideActivity extends BaseCommonActivity {

    /**
     * Gif动画
     */
    private GifImageView gifView;

    /**
     * activity jump delay millis
     *
     * @return long
     */
    private long delayMillis = 1000;

    @Override
    public int getViewLayout() {
        return R.layout.launch_activity_guide;
    }

    @Override
    public void initView() {
        gifView = findViewById(R.id.gifView);
        gifView.setImageResource(R.drawable.guide_bg);
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
        handler.postDelayed(this::toJumpActivity, delayMillis);
    }

    /**
     * 界面跳转,判断是跳转登录还是跳转主页
     */
    private void toJumpActivity() {
        if (BaseConstant.sharedPreferencesUtils.getBoolean(BaseConstant.SP.CACHE_IS_LOGIN)) {
            //跳转main模块主页
            RouteActivityUtils.toJumpMainModeIndexActivity(getContext(), this::finish);
        } else {
            //跳转登录模块主页
            RouteActivityUtils.toJumpLoginModeIndexActivity(getContext(), this::finish);
        }
    }

}
