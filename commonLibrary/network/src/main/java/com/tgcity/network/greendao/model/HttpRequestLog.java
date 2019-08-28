package com.tgcity.network.greendao.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author TGCity
 * 网络请求日志模型
 */
@Entity
public class HttpRequestLog {

    /**
     * 主键
     */
    @Id(autoincrement = true)
    private Long id;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 请求数据
     */
    private String requestData;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 请求状态
     */
    private int type;

    @Generated(hash = 78361351)
    public HttpRequestLog(Long id, String apiName, String requestData,
                          long createTime, int type) {
        this.id = id;
        this.apiName = apiName;
        this.requestData = requestData;
        this.createTime = createTime;
        this.type = type;
    }

    @Generated(hash = 503143878)
    public HttpRequestLog() {
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
