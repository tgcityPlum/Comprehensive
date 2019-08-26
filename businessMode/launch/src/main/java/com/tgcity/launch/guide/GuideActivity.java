package com.tgcity.launch.guide;

import android.os.Handler;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.ARouteConstant;
import com.tgcity.launch.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * launch模块--启动界面
 *
 */
@Route(path = ARouteConstant.LaunchMode.MAIN_FRAGMENT)
public class GuideActivity extends BaseCommonActivity {
    //Gif动画
    private GifImageView gifView;

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
        handler.postDelayed(this::toJumpActivity, 1000);
    }

    /**
     * 界面跳转
     */
    private void toJumpActivity() {

    }

}
