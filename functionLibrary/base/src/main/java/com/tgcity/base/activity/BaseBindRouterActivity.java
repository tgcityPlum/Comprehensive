package com.tgcity.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author TGCity
 * 基础的activity类
 * --绑定路由
 */
public abstract class BaseBindRouterActivity extends BaseBindViewActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册 路由
        ARouter.getInstance().inject(getContext());
    }

}
