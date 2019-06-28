package com.tgcity.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tgcity.base.R;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.LogUtils;

/**
 * 基础的activity类
 * --处理事件逻辑
 */
public abstract class BaseEventLogicActivity extends BaseBindViewActivity {
    //是否启动过，暂时仅仅用来在singleTask/singleInstance/singleTop模式下或其他类似场景下的不销毁Activity重新加载数据
    private boolean launched;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        depositBeforeAll();
        super.onCreate(savedInstanceState);
        depositParameter();

        initView();
        logBaseEventLogicActivity(getCurrentPageName(getString(R.string.base_event_logic_activity_initView)));

        setListener();
        launched = true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (isReLoadWithNewIntent()) {
            setIntent(putIntent() == null ? intent : putIntent());
            depositParameter();
            initView();
        }
        super.onNewIntent(intent);
    }

    private Intent putIntent() {
        return getIntent();
    }

    /**
     * 是否重新跳转
     */
    public boolean isReLoadWithNewIntent() {
        return true;
    }

    /**
     * 是否已启动
     */
    public boolean isLaunched() {
        return launched;
    }

    public void setListener() {
        logBaseEventLogicActivity(getCurrentPageName(getString(R.string.base_event_logic_activity_setListener)));
    }

    public abstract void initView();

    public void depositParameter() {
        logBaseEventLogicActivity(getCurrentPageName(getString(R.string.base_event_logic_activity_depositParameter)));
    }

    public void depositBeforeAll() {
        logBaseEventLogicActivity(getCurrentPageName(getString(R.string.base_event_logic_activity_depositBeforeAll)));
    }

    /**
     * 输出当前界面调用方法的日志
     */
    private void logBaseEventLogicActivity(String message) {
        if (BaseConstant.Power.isBaseEventLogicActivityLogShow) {
            LogUtils.d(message);
        }
    }

    @Override
    protected void onDestroy() {
        launched = false;
        super.onDestroy();
    }
}
