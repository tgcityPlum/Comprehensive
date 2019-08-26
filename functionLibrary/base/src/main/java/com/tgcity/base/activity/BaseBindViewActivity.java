package com.tgcity.base.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tgcity.base.R;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.ClearViewUtils;
import com.tgcity.base.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基础的activity类
 * --绑定view层
 */
public abstract class BaseBindViewActivity extends BaseOrientationActivity {
    //根布局控件
    private View rootView;
    //空布局，销毁时置空使用
    private View nullView;
    //Context
    private Context mContext;
    //ButterKnife unBinder
    private Unbinder unBind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        rootView = findViewById(android.R.id.content);//设置根布局控件
        if (rootView != null && getBackgroundColor() != 0) {
            rootView.setBackgroundColor(getBackgroundColor());
        }
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        setButterKnifeBind();
    }

    /**
     * get background color
     */
    public int getBackgroundColor() {
        return 0;
    }

    /**
     * abstract get layout id
     */
    public abstract int getViewLayout();

    /**
     * set butterKnife bind
     */
    private void setButterKnifeBind() {
        unBind = ButterKnife.bind(this);
        if (BaseConstant.Power.isBaseBindViewActivityLogShow) {
            LogUtils.d(getString(R.string.base_bind_view_activity_current_butterKnife));
        }
    }

    /**
     * get Context
     */
    public Context getContext() {
        return mContext;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //destroy unBinder
        if (unBind != null) {
            unBind.unbind();
            unBind = null;
        }
        //destroy rootView
        if (rootView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ClearViewUtils.clearAll(rootView);
        }

        nullView = new View(mContext);
        setContentView(nullView);
        nullView = null;
        mContext = null;
    }
}