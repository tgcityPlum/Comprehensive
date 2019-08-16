package com.tgcity.network.api;

import com.tgcity.common.bean.request.RegistrationUserInput;
import com.tgcity.common.bean.response.UserIdDto;
import com.tgcity.common.bean.response.result.HttpResultTZY;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService5100 {

    //用户注册
    @POST("Users/Register")
    Observable<HttpResultTZY<UserIdDto>> Register(@Body RegistrationUserInput registrationUserInput);

}
