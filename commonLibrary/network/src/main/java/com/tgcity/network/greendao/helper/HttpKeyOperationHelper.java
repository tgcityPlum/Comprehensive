package com.tgcity.network.greendao.helper;


import com.tgcity.network.greendao.HttpRequestLogDao;
import com.tgcity.network.greendao.model.HttpRequestLog;

/**
 * @author TGCity
 * HttpKey管理工具
 */
public class HttpKeyOperationHelper {
    /**
     * 同一接口 同一参数
     * 默认
     * ApiAndRequsEffectiveRegion内最多请求ApiAndRequsEffectiveFrequency次
     * 锁定后 ApiAndRequsEffectiveRegion后解锁
     * */
    private long ApiAndRequsEffectiveRegion;
    private int ApiAndRequsEffectiveFrequency;

    /**
     * 同一接口
     * 默认
     *ApiEffectiveRegion内最多请求ApiEffectiveFrequency次
     * 锁定后 ApiEffectiveRegion后解锁
     * */
    private long ApiEffectiveRegion;
    private int ApiEffectiveFrequency;

    /**
     * 所有接口
     * 默认
     * AllEffectiveRegion最多请求AllEffectiveFrequency次
     * 锁定后  AllEffectiveRegion后解锁
     * */
    private long AllEffectiveRegion;
    private int AllEffectiveFrequency;


    public HttpKeyOperationHelper setApiAndRequsEffective(long apiAndRequsEffectiveRegion, int apiAndRequsEffectiveFrequency) {
        ApiAndRequsEffectiveRegion = apiAndRequsEffectiveRegion;
        ApiAndRequsEffectiveFrequency = apiAndRequsEffectiveFrequency;
        return this;
    }

    public HttpKeyOperationHelper setApiEffective(long apiEffectiveRegion, int apiEffectiveFrequency) {
        ApiEffectiveRegion = apiEffectiveRegion;
        ApiEffectiveFrequency = apiEffectiveFrequency;
        return this;
    }


    public HttpKeyOperationHelper setAllEffective(long allEffectiveRegion, int allEffectiveFrequency) {
        AllEffectiveRegion = allEffectiveRegion;
        AllEffectiveFrequency = allEffectiveFrequency;
        return this;
    }


    /**
     * 生成单例
     * */
    private static class SingletonHolder {
        private static HttpKeyOperationHelper INSTANCE = new HttpKeyOperationHelper();
    }

    /**
     * 获得单例
     * */
    public static HttpKeyOperationHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 插入数据
     * */
    public void addKey(String apiName, String requestData) {

        HttpRequestLog httpRequestLog = new HttpRequestLog();
        httpRequestLog.setId(null);
        httpRequestLog.setApiName(apiName);
        httpRequestLog.setRequestData(requestData);
        httpRequestLog.setCreateTime(System.currentTimeMillis());
        httpRequestLog.setType(0);

        GreenDaoHelper.getInstance().getSession().insert(httpRequestLog);

    }

    /**
     * 检查是否恶意请求
     * */
    public boolean Effective(String apiName, String requestData) {

        int apiAndRequestSize = GreenDaoHelper.getInstance().getSession().queryBuilder(HttpRequestLog.class)
                .where(HttpRequestLogDao.Properties.ApiName.eq(apiName))
                .where(HttpRequestLogDao.Properties.RequestData.eq(requestData))
                .where(HttpRequestLogDao.Properties.CreateTime.ge(System.currentTimeMillis() - ApiAndRequsEffectiveRegion)).list().size();

        int apiSize = GreenDaoHelper.getInstance().getSession().queryBuilder(HttpRequestLog.class)
                .where(HttpRequestLogDao.Properties.ApiName.eq(apiName))
                .where(HttpRequestLogDao.Properties.CreateTime.ge(System.currentTimeMillis() - ApiEffectiveRegion)).list().size();

        int allSize = GreenDaoHelper.getInstance().getSession().queryBuilder(HttpRequestLog.class)
                .where(HttpRequestLogDao.Properties.CreateTime.ge(System.currentTimeMillis() - AllEffectiveRegion)).list().size();


        GreenDaoHelper.getInstance().getSession().queryBuilder(HttpRequestLog.class)
                .where(HttpRequestLogDao.Properties.CreateTime.lt(System.currentTimeMillis() - AllEffectiveRegion))
                .buildDelete().executeDeleteWithoutDetachingEntities();


        if (apiAndRequestSize > ApiAndRequsEffectiveFrequency) {
            return true;
        } else if (apiSize > ApiEffectiveFrequency) {
            return true;
        } else if (allSize > AllEffectiveFrequency) {
            return true;
        } else {
            return false;
        }
    }
}
