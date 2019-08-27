package com.tgcity.demo.index;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.constant.ARouteConstant;

public class AppIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ARouter.getInstance().build(ARouteConstant.LaunchMode.MAIN_FRAGMENT).navigation();
        finish();
    }
}
