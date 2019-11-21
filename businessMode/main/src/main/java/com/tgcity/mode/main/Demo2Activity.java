package com.tgcity.mode.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.constant.RouteConstant;

@Route(path = RouteConstant.MainMode.MAIN_ACTIVITY_DEMO2)
public class Demo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
    }
}
