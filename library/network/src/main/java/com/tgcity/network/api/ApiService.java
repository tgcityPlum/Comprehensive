package com.tgcity.network.api;

import com.tgcity.resource.bean.request.WeixinBody;
import com.tgcity.resource.bean.response.PictureDto;
import com.tgcity.resource.bean.response.TestDataItemBean;
import com.tgcity.resource.bean.response.WeixinToken;
import com.tgcity.network.bean.result.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 作者：TGCity by Administrator on 2018/7/23 09：56
 * API接口
 */

public interface ApiService {

    //获取微信小程序token
    @GET("cgi-bin/token")
    Observable<WeixinToken> getWeixinToken(@Query("grant_type") String grant_type, @Query("appid") String appid, @Query("secret") String secret);

    //获取微信小程序二维码
    @POST("wxa/getwxacode")
    Observable<ResponseBody> getwxacode(@Query("access_token") String access_token, @Body WeixinBody weixinBody);

    //图片上传
    @Multipart
    @POST("files/upload")
    Observable<HttpResult<PictureDto>> picturesUpload(@Query("seoFileName") String name, @Part MultipartBody.Part file);

    //测试接口
    @GET("uz-appmgrv2/api/app/usercenter/list")
    Observable<HttpResult<List<TestDataItemBean>>> getTestList(@Query("access_token") String token);
}