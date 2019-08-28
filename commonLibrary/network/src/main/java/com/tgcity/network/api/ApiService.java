package com.tgcity.network.api;

import com.tgcity.base.network.bean.request.WeiXinBody;
import com.tgcity.base.network.bean.response.PictureDto;
import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.base.network.bean.result.HttpResult;

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
 * @author TGCity
 * API接口
 */

public interface ApiService {

    /**
     * 获取微信小程序二维码
     * @param accessToken  String
     * @param body WeiXinBody
     * @return ResponseBody
     */
    @POST("wxa/getwxacode")
    Observable<ResponseBody> getwxacode(@Query("access_token") String accessToken, @Body WeiXinBody body);

    /**
     * 图片上传
     * @param name String
     * @param file MultipartBody.Part
     * @return HttpResult<PictureDto>
     */
    @Multipart
    @POST("files/upload")
    Observable<HttpResult<PictureDto>> picturesUpload(@Query("seoFileName") String name, @Part MultipartBody.Part file);

    /**
     * 测试接口
     * @param token String
     * @return HttpResult<List<TestDataItemBean>>
     */
    @GET("uz-appmgrv2/api/app/usercenter/list")
    Observable<HttpResult<List<TestDataItemBean>>> getTestList(@Query("access_token") String token);
}