package com.tgcity.network.api;


import com.tgcity.base.network.bean.request.QueryMessagesInput;
import com.tgcity.base.network.bean.result.PagedListResultDto;
import com.tgcity.base.network.bean.response.QueryMessagesOutput;
import com.tgcity.base.network.bean.response.SettingsDto;
import com.tgcity.network.bean.result.HttpCommonResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author TGCity
 */
public interface ApiService5101 {

    /**
     * 个人中心消息--根据类型获取消息列表
     * @param  input QueryMessagesInput
     * @return  HttpCommonResult<PagedListResultDto<QueryMessagesOutput>>
     */
    @POST("Notification/Messages/Query")
    Observable<HttpCommonResult<PagedListResultDto<QueryMessagesOutput>>> queryMessages(@Body QueryMessagesInput input);

    /**
     * 获取配置信息
     * @param identification String
     * @return HttpCommonResult<SettingsDto>
     */
    @POST("SiteSettings/Get")
    Observable<HttpCommonResult<SettingsDto>> querySiteSettings(@Query("identification") String identification);

}
