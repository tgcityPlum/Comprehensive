package com.tgcity.network.base;

import android.content.Context;

import com.tgcity.base.application.BaseApplication;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.network.greendao.helper.GreenDaoHelper;
import com.tgcity.base.utils.SharedPreferencesUtils;

public class NetworkApplication extends BaseApplication {
    //静态单例
    private static NetworkApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instances == null) {
            instances = this;
        }
        //初始化sp对象
//        initNetWork(false, NetworkConstant.SERVER_OLD_API_URL, NetworkConstant.SERVER_DEFAULT_SHARE_IMAGE_URL, NetworkConstant.SERVER_IMAGE_UPLOAD_API_URL, NetworkConstant.SERVER_WX_API_URL, NetworkConstant.SERVER_NEW_API_URL);
        //数据库初始化
        GreenDaoHelper.getInstance();
        NetworkConstant.sharedPreferencesUtils = new SharedPreferencesUtils(getInstances(), BaseConstant.SP.CONFIG, Context.MODE_PRIVATE);
    }

    public static NetworkApplication getInstances() {
        if (instances == null) {
            instances = new NetworkApplication();
        }
        return instances;
    }

}
