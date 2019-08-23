package com.tgcity.mode.news.base;

import com.tgcity.mode.news.net.NewsRetrofitUtils;
import com.tgcity.network.base.NetworkApplication;
import com.tgcity.network.base.NetworkConstant;

/**
 * Home模块中的application
 */
public class NewsApplication extends NetworkApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initNetWork(false, NetworkConstant.ServiceFlag.SERVER_DEFAULT);
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
        //网络请求初始化
        NewsRetrofitUtils.getInstance().init(this, getCacheVersion, server);

    }

}
