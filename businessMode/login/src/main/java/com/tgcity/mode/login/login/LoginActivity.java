package com.tgcity.mode.login.login;

import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.interfaces.RouteNavigationCallBack;
import com.tgcity.base.utils.PermissionUtils;
import com.tgcity.base.utils.RouteIntentUtils;
import com.tgcity.mode.login.R;

/**
 * @author TGCity
 * 登录模块--登录界面
 */
@Route(path = RouteConstant.LoginMode.LOGIN_ACTIVITY_LOGIN)
public class LoginActivity extends BaseCommonActivity {

    private Button btnLogin;

    @Override
    public int getViewLayout() {
        return R.layout.login_activity_login;
    }

    @Override
    public void initView() {
        findViews();

        PermissionUtils.getPermissionsResult(LoginActivity.this);

        clickListener();
    }

    private void clickListener() {
        btnLogin.setOnClickListener(view -> {
            //更新缓存“是否登录”状态
            BaseConstant.sharedPreferencesUtils.put(BaseConstant.SP.CACHE_IS_LOGIN, true);
            //跳转main模块主页
            RouteIntentUtils.onMainModeIndexActivity(getContext(), new RouteNavigationCallBack() {
                @Override
                public void onArrivalBack() {
                    finish();
                }

                @Override
                public void onLostBack() {

                }
            });
        });
    }

    private void findViews() {
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

}
