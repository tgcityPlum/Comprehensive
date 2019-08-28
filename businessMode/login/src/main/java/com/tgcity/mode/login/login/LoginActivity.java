package com.tgcity.mode.login.login;

import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.mode.login.R;

public class LoginActivity extends BaseCommonActivity {

    @Override
    public int getViewLayout() {
        return R.layout.login_activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

}
