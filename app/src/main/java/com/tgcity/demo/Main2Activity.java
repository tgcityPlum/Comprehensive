package com.tgcity.demo;

import com.tgcity.base.activity.BaseFatherActivity;

public class Main2Activity extends BaseFatherActivity {

    @Override
    public String getCurrentPage() {
        return "跳转界面";
    }

    @Override
    public int getViewLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {

    }



}
