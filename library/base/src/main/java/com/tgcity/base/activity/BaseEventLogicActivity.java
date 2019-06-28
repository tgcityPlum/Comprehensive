package com.tgcity.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
        LogUtils.d("当前界面{ " + getLocalClassName() + " 正在调用initView()");

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
        LogUtils.d("当前界面{ " + getCurrentPageName() + " 正在调用setListener()");
    }

    public abstract void initView();

    public void depositParameter() {
        LogUtils.d("当前界面{ " + getCurrentPageName() + " 正在调用depositParameter()");
    }

    public void depositBeforeAll() {
        LogUtils.d("当前界面{ " + getCurrentPageName() + " 正在调用depositBeforeAll()");
    }

    public abstract String getCurrentPage();

    private String getCurrentPageName() {
        return getCurrentPage() + " } ==>" + getLocalClassName();
    }

    @Override
    protected void onDestroy() {
        launched = false;
        super.onDestroy();
    }
}
