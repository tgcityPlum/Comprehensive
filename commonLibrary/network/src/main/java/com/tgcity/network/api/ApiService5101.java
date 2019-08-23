package com.tgcity.network.api;


import com.tgcity.base.network.bean.request.QueryMessagesInput;
import com.tgcity.base.network.bean.result.PagedListResultDto;
import com.tgcity.base.network.bean.response.QueryMessagesOutput;
import com.tgcity.base.network.bean.response.SettingsDto;
import com.tgcity.network.bean.result.HttpResultTZY;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService5101 {

    /**
     * 个人中心消息--根据类型获取消息列表
     */
    @POST("Notification/Messages/Query")
    Observable<HttpResultTZY<PagedListResultDto<QueryMessagesOutput>>> QueryMessages(@Body QueryMessagesInput input);

    //获取配置信息
    @POST("SiteSettings/Get")
    Observable<HttpResultTZY<SettingsDto>> querySiteSettings(@Query("identification") String identification);

}
