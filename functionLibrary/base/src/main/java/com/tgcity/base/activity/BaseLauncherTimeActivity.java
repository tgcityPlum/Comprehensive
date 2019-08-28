package com.tgcity.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tgcity.base.R;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.LogUtils;

/**
 * @author TGCity
 * 基础的activity
 * --处理Activity的启动时间
 */
public abstract class BaseLauncherTimeActivity extends BaseImmersionBarActivity {
    /**
     * time
     */
    private long currentTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (BaseConstant.Power.IS_LAUNCHER_TIME_ACTIVITY_LOG_SHOW) {
            currentTime = System.currentTimeMillis();
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus && BaseConstant.Power.IS_LAUNCHER_TIME_ACTIVITY_LOG_SHOW) {
            LogUtils.d(getString(R.string.base_memory_activity_current_page_name, getCurrentPage(), getLocalClassName(), transformTime(System.currentTimeMillis() - currentTime)));
        }
    }

    /**
     * 转换当前时间
     * --long 转换成 xx分xx秒xx毫秒
     */
    private String transformTime(long timeMillis) {
        String time;

        if (timeMillis >= 1000) {
            //大于或等于1s
            if (timeMillis >= 60000) {
                time = timeMillis / 60000 + "min";
            } else {
                time = timeMillis / 1000 + "s";
            }
        } else {
            time = timeMillis + "ms";
        }
        return "启动时间是" + time;
    }
}
