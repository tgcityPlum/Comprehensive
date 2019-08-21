package com.tgcity.network.base;

import android.content.Context;

import com.tgcity.base.application.BaseApplication;
import com.tgcity.network.greendao.helper.GreenDaoHelper;
import com.tgcity.network.retrofit.NetworkRetrofitUtils;
import com.tgcity.network.utils.SPUtils;

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
        initNetWork(false, NetworkConstant.ServiceFlag.SERVER_DEFAULT);
        NetworkConstant.spUtils = new SPUtils(getInstances(), NetworkConstant.SP.CONFIG, Context.MODE_PRIVATE);
    }

    /**
     * 初始化网络/本地数据库授权相关
     *
     * @param getCacheVersion 是否需要获取版本信息
     * @param server          要初始化的服务器地址，详见AppConstant中的API地址初始化控制部分
     */
    public void initNetWork(boolean getCacheVersion, int... server) {
        //对xxx内部so文件授权
//        NativeUtil.soSignatures(this);
        //数据库初始化
        GreenDaoHelper.getInstance();
        //网络请求初始化
        NetworkRetrofitUtils.getInstance().init(this, getCacheVersion, server);

    }

    public static NetworkApplication getInstances() {
        if (instances == null) {
            instances = new NetworkApplication();
        }
        return instances;
    }

}
