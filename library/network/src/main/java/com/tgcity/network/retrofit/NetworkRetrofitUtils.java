package com.tgcity.network.retrofit;

import android.app.Application;

import com.google.gson.Gson;
import com.tgcity.base.utils.LogUtils;
import com.tgcity.network.api.ApiService;
import com.tgcity.network.api.ApiService5001;
import com.tgcity.network.api.ApiService5100;
import com.tgcity.network.api.ApiService5101;
import com.tgcity.network.base.NetworkConstant;
import com.tgcity.network.bean.result.HttpResult;
import com.tgcity.network.bean.result.HttpResultTZY;
import com.tgcity.network.cache.model.CacheMode;
import com.tgcity.network.callback.SimpleCallBack;
import com.tgcity.resource.bean.request.QueryMatchAndMismatchCollegeInput;
import com.tgcity.resource.bean.request.QueryMessagesInput;
import com.tgcity.resource.bean.request.RegistrationUserInput;
import com.tgcity.resource.bean.request.WeixinBody;
import com.tgcity.resource.bean.response.CacheVersionDto;
import com.tgcity.resource.bean.response.MessagesUnreadCountOutput;
import com.tgcity.resource.bean.response.PagedListResultDto;
import com.tgcity.resource.bean.response.PictureDto;
import com.tgcity.resource.bean.response.QueryMajorChooseSubjectOutput;
import com.tgcity.resource.bean.response.QueryMatchAndMismatchCollegeOutput;
import com.tgcity.resource.bean.response.QueryMessagesOutput;
import com.tgcity.resource.bean.response.SettingsDto;
import com.tgcity.resource.bean.response.TestDataItemBean;
import com.tgcity.resource.bean.response.UserIdDto;
import com.tgcity.resource.bean.response.WeixinToken;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpDate;

/*
 * 作者：TGCity by Administrator on 2018/7/23
 * HttpData单例
 * 暴露使用
 * 需在Application初始化
 * */
@SuppressWarnings("ALL")
public class NetworkRetrofitUtils extends HttpRetrofitUtils {

    public ApiService service;
    public ApiService service_weixin;
    public ApiService service_image;
    public ApiService5001 service_tzy5001;
    public ApiService5100 service_tzy5100;
    public ApiService5101 service_tzy5101;
    public ApiService service_ImageDownload;

    /*
     * 双重判断加锁单例
     * 保证异步处理安全
     * */
    private volatile static NetworkRetrofitUtils instance = null;

    //获取单例
    public static NetworkRetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (HttpDate.class) {
                if (instance == null) {
                    instance = new NetworkRetrofitUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 初始化
     *
     * @param mContext        你懂的
     * @param getCacheVersion 是否需要获取版本信息
     * @param server          要初始化的服务器地址，详见AppConstant中的API地址初始化控制部分
     */
    @Override
    public void init(Application mContext, boolean getCacheVersion, int... server) {
        for (int i = 0; i < server.length; i++) {
            LogUtils.e(NetworkConstant.SYSTEM_TAG, server[i]);
            switch (server[i]) {
                case NetworkConstant.ServiceFlag.SERVER_WX:
                    service_weixin = getRetrofitWeiXin().create(ApiService.class);
                    break;
                case NetworkConstant.ServiceFlag.SERVER_IMAGE_UPLOAD:
                    service_image = getRetrofitImage().create(ApiService.class);
                    break;
                case NetworkConstant.ServiceFlag.SERVER_NEW:
                    service_tzy5001 = getRetrofitTZY5001().create(ApiService5001.class);
                    service_tzy5100 = getRetrofitTZY5100().create(ApiService5100.class);
                    service_tzy5101 = getRetrofitTZY5101().create(ApiService5101.class);
                    break;
                case NetworkConstant.ServiceFlag.SERVER_DEFAULT:
                    service = getRetrofit().create(ApiService.class);
                    break;
                case NetworkConstant.ServiceFlag.SERVER_DEFAULT_SHARE_IMAGE:
                    service_ImageDownload = getRetrofit().create(ApiService.class);
                    break;
                default:
                    break;
            }
        }
        LogUtils.e(
                NetworkConstant.SYSTEM_TAG, "各种API地址初始化结果\n" +
                        "微信地址是否初始化：" + (service_weixin != null) + "\n" +
                        "图片上传地址是否初始化：" + (service_image != null) + "\n" +
                        "老版本API地址是否初始化：" + (service != null) + "\n" +
                        "新版本API地址是否初始化：" + "\n" +
                        (service_tzy5001 != null) + "\n" +
                        (service_tzy5100 != null) + "\n" +
                        (service_tzy5101 != null) + "\n" +
                        "是否需要获取版本信息：" + getCacheVersion
        );
        super.init(mContext, getCacheVersion);
        if (getCacheVersion) {
            cacheVersion(new SimpleCallBack<CacheVersionDto>() {
                @Override
                public void onError(Throwable e) {
                    NetworkConstant.Cache_College = NetworkConstant.spUtils.getInt(NetworkConstant.SP.CACHE_COLLEGE, 1);
                    NetworkConstant.Cache_Major = NetworkConstant.spUtils.getInt(NetworkConstant.SP.CACHE_MAJOR, 1);
                    NetworkConstant.Cache_PCL = NetworkConstant.spUtils.getInt(NetworkConstant.SP.CACHE_PCL, 1);
                    NetworkConstant.Cache_Other = NetworkConstant.spUtils.getInt(NetworkConstant.SP.CACHE_OTHER, 1);
                }

                @Override
                public void onNext(CacheVersionDto cacheVersionDtos) {


                    NetworkConstant.spUtils.put(NetworkConstant.SP.CACHE_COLLEGE, cacheVersionDtos.getCollege());
                    NetworkConstant.spUtils.put(NetworkConstant.SP.CACHE_MAJOR, cacheVersionDtos.getMajor());
                    NetworkConstant.spUtils.put(NetworkConstant.SP.CACHE_PCL, cacheVersionDtos.getPCL());
                    NetworkConstant.spUtils.put(NetworkConstant.SP.CACHE_OTHER, cacheVersionDtos.getOther());

                    NetworkConstant.Cache_College = cacheVersionDtos.getCollege();
                    NetworkConstant.Cache_Major = cacheVersionDtos.getMajor();
                    NetworkConstant.Cache_PCL = cacheVersionDtos.getPCL();
                    NetworkConstant.Cache_Other = cacheVersionDtos.getOther();
                }
            });
        }
    }

    /**
     * 缓存配置读取
     * 不缓存
     *
     * @param callBack
     */
    public void cacheVersion(SimpleCallBack<CacheVersionDto> callBack) {
        Observable<CacheVersionDto> observable = service_tzy5101.querySiteSettings("AndroidCacheConfig").map(new Function<HttpResultTZY<SettingsDto>, CacheVersionDto>() {
            @Override
            public CacheVersionDto apply(HttpResultTZY<SettingsDto> pagedListResultDtoHttpResultTZY) throws Exception {
                CacheVersionDto cacheVersionDto = new CacheVersionDto();
                Gson gson = new Gson();
                cacheVersionDto = gson.fromJson(pagedListResultDtoHttpResultTZY.getResult().getSettingsJson(), CacheVersionDto.class);
                return cacheVersionDto;
            }
        });
        toObservable(new Builder(observable)
                .setApiName("cacheVersion")
                .setHttpResultFormatting(false)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }


    // 获取微信小程序token
    public void getwxacodeBitmap(LifecycleTransformer lifecycleTransformer, String grant_type, String appid, String secret, SimpleCallBack<WeixinToken> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_weixin.getWeixinToken(grant_type, appid, secret))
                .setApiName("getwxacodeBitmap")
                .setRequestData(grant_type, appid, secret, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(false)
                .setCacheTime(NetworkConstant.cache_a_second)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    // 获取微信小程序码
    public void getwxacode(LifecycleTransformer lifecycleTransformer, String access_token, WeixinBody weixinBody, final SimpleCallBack<ResponseBody> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_weixin.getwxacode(access_token, weixinBody))
                .setApiName("getwxacode")
                .setRequestData(access_token, weixinBody, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(false)
                .setCacheTime(NetworkConstant.cache_a_second)
                .setCacheMode(CacheMode.NO_CACHE), callBack);

    }

    //图片上传
    public void picturesUpload(String fileName, String path, SimpleCallBack<HttpResult<PictureDto>> callBack) {
        File file = new File(path);
        String suffix = "jpg";
        try {
            String[] str = path.split(",");
            if (str.length > 1) {
                suffix = str[str.length - 1];
            } else {
                suffix = "jpg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            suffix = "jpg";
        } finally {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/" + suffix), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            toObservable(new Builder(service_image.picturesUpload(fileName, body))
                    .setApiName("picturesUpload")
                    .setHttpResultFormatting(false)
                    .setCacheMode(CacheMode.NO_CACHE), callBack);
        }
    }

    //图片上传
    public void getTestList(String access_token, SimpleCallBack<HttpResult<List<TestDataItemBean>>> callBack) {
        toObservable(null, new Builder(service.getTestList(access_token))
                .setApiName("getTestList")
                .setRequestData(access_token, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(false)
                .setCacheTime(NetworkConstant.cache_a_second)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }


    /*****************************************************************我是分割线，以下开始为新接口*******************************************************************************/

    //用户注册
    public void Register(LifecycleTransformer lifecycleTransformer, RegistrationUserInput registrationUserInput, SimpleCallBack<UserIdDto> callBack) {
        toObservable(new Builder(service_tzy5100.Register(registrationUserInput))
                .setApiName("Register")
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /*****************************************************************5100结束*******************************************************************************/
    //选科模块--获得匹配和不匹配院校
    public void queryMatchAndMismatchCollege(LifecycleTransformer lifecycleTransformer, QueryMatchAndMismatchCollegeInput mismatchCollegeInput, SimpleCallBack<QueryMatchAndMismatchCollegeOutput> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5001.queryMatchAndMismatchCollege(mismatchCollegeInput))
                .setApiName("queryMatchAndMismatchCollege")
                .setRequestData(mismatchCollegeInput, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.FIRSTCACHE), callBack);
    }

    //选科模块--选科专业明细查询
    public void queryMajorChooseSubject(LifecycleTransformer lifecycleTransformer, int provinceId, String MajorCode, int year, SimpleCallBack<List<QueryMajorChooseSubjectOutput>> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5001.queryMajorChooseSubject(provinceId, MajorCode, year))
                .setApiName("queryMajorChooseSubject")
                .setRequestData(provinceId, MajorCode, year, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /*****************************************************************5001结束*******************************************************************************/


    //个人中心消息--获取用户未读消息数量
    public void MessagesUnreadCount(LifecycleTransformer lifecycleTransformer, int userNumId, boolean isFillGroupMessage, SimpleCallBack<MessagesUnreadCountOutput> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5101.MessagesUnreadCount(userNumId, isFillGroupMessage))
                .setApiName("MessagesUnreadCount")
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    //个人中心消息--根据类型获取消息列表
    public void QueryMessages(LifecycleTransformer lifecycleTransformer, QueryMessagesInput input, SimpleCallBack<PagedListResultDto<QueryMessagesOutput>> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_tzy5101.QueryMessages(input))
                .setApiName("QueryMessages")
                .setHttpResultFormatting(true)
                .setExtraRemark(NetworkConstant.API_SERVICE_TZY)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /*****************************************************************5101结束*******************************************************************************/

}
