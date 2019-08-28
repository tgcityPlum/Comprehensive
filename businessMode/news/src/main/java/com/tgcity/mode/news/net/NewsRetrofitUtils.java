package com.tgcity.mode.news.net;

import android.app.Application;

import com.tgcity.base.network.bean.request.QueryMessagesInput;
import com.tgcity.base.network.bean.result.PagedListResultDto;
import com.tgcity.base.network.bean.response.QueryMajorChooseSubjectOutput;
import com.tgcity.base.network.bean.response.QueryMessagesOutput;
import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.network.base.NetworkConstant;
import com.tgcity.network.cache.model.CacheMode;
import com.tgcity.network.callback.AbstractSimpleCallBack;
import com.tgcity.network.retrofit.NetworkRetrofitUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public class NewsRetrofitUtils extends NetworkRetrofitUtils {

    /*
     * 双重判断加锁单例
     * 保证异步处理安全
     * */
    private volatile static NewsRetrofitUtils instance = null;

    //获取单例
    public static NewsRetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (NewsRetrofitUtils.class) {
                if (instance == null) {
                    instance = new NewsRetrofitUtils();
                }
            }
        }
        return instance;
    }

    @Override
    public void init(Application mContext, boolean getCacheVersion, int... server) {
        super.init(mContext, getCacheVersion, server);
    }

    //测试数组
    public void getTestList(String access_token, AbstractSimpleCallBack<HttpResult<List<TestDataItemBean>>> callBack) {
        toObservable(null, new Builder(service.getTestList(access_token))
                .setApiName("getTestList")
                .setRequestData(access_token, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(false)
                .setCacheTime(NetworkConstant.cache_a_second)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /*****************************************************************我是分割线，以下开始为新接口*******************************************************************************/

    //选科模块--选科专业明细查询
    public void queryMajorChooseSubject(LifecycleTransformer lifecycleTransformer, int provinceId, String MajorCode, int year, AbstractSimpleCallBack<List<QueryMajorChooseSubjectOutput>> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5001.queryMajorChooseSubject(provinceId, MajorCode, year))
                .setApiName("queryMajorChooseSubject")
                .setRequestData(provinceId, MajorCode, year, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /*****************************************************************5001结束*******************************************************************************/

    //个人中心消息--根据类型获取消息列表
    public void QueryMessages(LifecycleTransformer lifecycleTransformer, QueryMessagesInput input, AbstractSimpleCallBack<PagedListResultDto<QueryMessagesOutput>> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5101.queryMessages(input))
                .setApiName("queryMessages")
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /*****************************************************************5101结束*******************************************************************************/

}
