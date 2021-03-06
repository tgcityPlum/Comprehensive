package com.tgcity.network.retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.network.bean.request.WeiXinBody;
import com.tgcity.base.network.bean.response.CacheVersionDto;
import com.tgcity.base.network.bean.response.PictureDto;
import com.tgcity.base.network.bean.response.SettingsDto;
import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.base.utils.LogUtils;
import com.tgcity.base.utils.SharedPreferencesUtils;
import com.tgcity.network.api.ApiService;
import com.tgcity.network.api.ApiService5001;
import com.tgcity.network.api.ApiService5101;
import com.tgcity.network.base.NetworkConstant;
import com.tgcity.network.bean.result.HttpCommonResult;
import com.tgcity.network.cache.model.CacheMode;
import com.tgcity.network.callback.AbstractSimpleCallBack;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpDate;

/**
 * @author TGCity
 * HttpData单例
 * 暴露使用
 * 需在Application初始化
 */
@SuppressWarnings("ALL")
public class NetworkRetrofitUtils extends HttpRetrofitUtils {

    public ApiService service;
    public ApiService service_weixin;
    public ApiService service_image;
    public ApiService5001 service_tzy5001;
    public ApiService5101 service_tzy5101;
    public ApiService service_ImageDownload;

    /**
     * 双重判断加锁单例
     * 保证异步处理安全
     */
    private volatile static NetworkRetrofitUtils instance = null;

    /**
     * 获取单例
     * @return NetworkRetrofitUtils
     */
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
    public void init(Context mContext, boolean getCacheVersion, int... server) {
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
                        "默认版本API地址是否初始化：" + (service != null) + "\n" +
                        "新版本API地址是否初始化：" + "\n" +
                        (service_tzy5001 != null) + "\n" +
                        (service_tzy5101 != null) + "\n" +
                        "是否需要获取版本信息：" + getCacheVersion
        );
        super.init(mContext, getCacheVersion);

        final SharedPreferencesUtils spUtils = NetworkConstant.sharedPreferencesUtils;

        if (getCacheVersion) {
            cacheVersion(new AbstractSimpleCallBack<CacheVersionDto>() {
                @Override
                public void onError(Throwable e) {
                    NetworkConstant.Cache_College = spUtils.getInt(BaseConstant.SP.CACHE_COLLEGE, 1);
                    NetworkConstant.Cache_Major = spUtils.getInt(BaseConstant.SP.CACHE_MAJOR, 1);
                    NetworkConstant.Cache_PCL = spUtils.getInt(BaseConstant.SP.CACHE_PCL, 1);
                    NetworkConstant.Cache_Other = spUtils.getInt(BaseConstant.SP.CACHE_OTHER, 1);
                }

                @Override
                public void onNext(CacheVersionDto cacheVersionDtos) {

                    spUtils.put(BaseConstant.SP.CACHE_COLLEGE, cacheVersionDtos.getCollege());
                    spUtils.put(BaseConstant.SP.CACHE_MAJOR, cacheVersionDtos.getMajor());
                    spUtils.put(BaseConstant.SP.CACHE_PCL, cacheVersionDtos.getPCL());
                    spUtils.put(BaseConstant.SP.CACHE_OTHER, cacheVersionDtos.getOther());

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
    public void cacheVersion(AbstractSimpleCallBack<CacheVersionDto> callBack) {
        Observable<CacheVersionDto> observable = service_tzy5101.querySiteSettings("AndroidCacheConfig").map(new Function<HttpCommonResult<SettingsDto>, CacheVersionDto>() {
            @Override
            public CacheVersionDto apply(HttpCommonResult<SettingsDto> result) throws Exception {
                CacheVersionDto cacheVersionDto = new CacheVersionDto();
                Gson gson = new Gson();
                cacheVersionDto = gson.fromJson(result.getResult().getSettingsJson(), CacheVersionDto.class);
                return cacheVersionDto;
            }
        });
        toObservable(new Builder(observable)
                .setApiName("cacheVersion")
                .setHttpResultFormatting(false)
                .setCacheMode(CacheMode.NO_CACHE), callBack);
    }

    /**
     * 获取微信小程序码
     *
     * @param lifecycleTransformer LifecycleTransformer
     * @param accessToken          String
     * @param weixinBody           WeiXinBody
     * @param callBack             AbstractSimpleCallBack
     */
    public void getwxacode(LifecycleTransformer lifecycleTransformer, String accessToken, WeiXinBody weixinBody, final AbstractSimpleCallBack<ResponseBody> callBack) {
        toObservable(lifecycleTransformer, new Builder(service_weixin.getwxacode(accessToken, weixinBody))
                .setApiName("getwxacode")
                .setRequestData(accessToken, weixinBody, NetworkConstant.Cache_Other)
                .setHttpResultFormatting(false)
                .setCacheTime(NetworkConstant.cache_a_second)
                .setCacheMode(CacheMode.NO_CACHE), callBack);

    }

    /**
     * 图片上传
     *
     * @param fileName String
     * @param path     String
     * @param callBack AbstractSimpleCallBack
     */
    public void picturesUpload(String fileName, String path, AbstractSimpleCallBack<HttpResult<PictureDto>> callBack) {
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

}
