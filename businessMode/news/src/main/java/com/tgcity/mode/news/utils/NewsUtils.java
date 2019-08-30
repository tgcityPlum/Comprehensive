package com.tgcity.mode.news.utils;

import android.content.Context;

import com.tgcity.mode.news.net.NewsRetrofitUtils;

/**
 * @author TGCity
 * @date 2019/8/30
 * @describe news mode utils
 */
public class NewsUtils {

    /**
     * 初始化网络/本地数据库授权相关
     *
     * @param context Context
     * @param getCacheVersion 是否需要获取版本信息
     * @param server          要初始化的服务器地址，详见AppConstant中的API地址初始化控制部分
     */
    public static void initNetWork(Context context, boolean getCacheVersion, int... server) {
        //对xxx内部so文件授权
//        NativeUtil.soSignatures(this);

        //网络请求初始化
        NewsRetrofitUtils.getInstance().init(context, getCacheVersion, server);

    }
}
