package com.tgcity.mode.news.net;

import android.content.Context;

import com.tgcity.base.network.bean.request.QueryMessagesInput;
import com.tgcity.base.network.bean.response.QueryMajorChooseSubjectOutput;
import com.tgcity.base.network.bean.response.QueryMessagesOutput;
import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.base.network.bean.result.PagedListResultDto;
import com.tgcity.network.base.NetworkConstant;
import com.tgcity.network.cache.model.CacheMode;
import com.tgcity.network.callback.AbstractSimpleCallBack;
import com.tgcity.network.retrofit.NetworkRetrofitUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author TGCity
 */
public class NewsRetrofitUtils extends NetworkRetrofitUtils {

    /**
     * 双重判断加锁单例
     * 保证异步处理安全
     */
    private volatile static NewsRetrofitUtils instance = null;

    /**
     * is init
     */
    private boolean isInit = false;

    /**
     * 获取单例
     *
     * @return NewsRetrofitUtils
     */
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
    public void init(Context mContext, boolean getCacheVersion, int... server) {
        if (!getIsInit()) {
            super.init(mContext, getCacheVersion, server);
            isInit = true;
        }
    }

    private boolean getIsInit() {
        return isInit;
    }

    /**
     * 测试数组
     *
     * @param accessToken String
     * @param callBack     AbstractSimpleCallBack
     */
    public void getTestList(String accessToken, AbstractSimpleCallBack<HttpResult<List<TestDataItemBean>>> callBack) {
        toObservable(null, new Builder(service.getTestList(accessToken))
                .setApiName("getTestList")
                .setRequestData(accessToken, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(false)
                .setCacheTime(NetworkConstant.cache_a_second)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /**
     * 选科模块--选科专业明细查询
     *
     * @param lifecycleTransformer LifecycleTransformer
     * @param provinceId           int
     * @param majorCode            String
     * @param year                 int
     * @param callBack             AbstractSimpleCallBack
     */
    public void queryMajorChooseSubject(LifecycleTransformer lifecycleTransformer, int provinceId, String majorCode, int year, AbstractSimpleCallBack<List<QueryMajorChooseSubjectOutput>> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5001.queryMajorChooseSubject(provinceId, majorCode, year))
                .setApiName("queryMajorChooseSubject")
                .setRequestData(provinceId, majorCode, year, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /**
     * 个人中心消息--根据类型获取消息列表
     *
     * @param lifecycleTransformer LifecycleTransformer
     * @param input                QueryMessagesInput
     * @param callBack             AbstractSimpleCallBack
     */
    public void queryMessages(LifecycleTransformer lifecycleTransformer, QueryMessagesInput input, AbstractSimpleCallBack<PagedListResultDto<QueryMessagesOutput>> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5101.queryMessages(input))
                .setApiName("queryMessages")
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

}
