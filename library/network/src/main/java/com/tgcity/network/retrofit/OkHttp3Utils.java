package com.tgcity.network.retrofit;

import com.tgcity.network.base.NetworkConstant;
import com.tgcity.common.bean.response.NetworkResponseEntity;
import com.tgcity.network.utils.MD5Util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import static okhttp3.internal.Util.UTF_8;

//import com.xietong.network.utils.HttpsUtils;

/*
 *作者：TGCity by Administrator on 2018/7/23
 * okHttp的配置
 * 以及公共请求头配置地址
 */
public class OkHttp3Utils {
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取OkHttpClient对象
     */
    public static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //添加拦截器
                    .addInterceptor(mTokenInterceptor)
                    //设置请求读写的超时时间
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
//                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
        }
        return mOkHttpClient;
    }

    /**
     * 云端响应头拦截器
     * 用于添加统一请求头  请按照自己的需求添加
     */
    private static final Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            String signatures = "";
            HttpUrl oldHttpUrl = originalRequest.url();
            if (oldHttpUrl.queryParameterNames().size() > 0) {
                String original = oldHttpUrl.encodedQuery() + "&1ebc37b92a8d488fb7e1b44594a340dd";
                signatures = MD5Util.getMD5(original.toLowerCase()).toLowerCase();
            } else {
                if (originalRequest.body() != null) {
                    try {
                        Buffer buffer = new Buffer();
                        originalRequest.body().writeTo(buffer);
                        Charset charset = Charset.forName("UTF-8");
                        MediaType contentType = originalRequest.body().contentType();
                        if (contentType != null) {
                            charset = contentType.charset(UTF_8);
                        }
                        String original = buffer.readString(charset) + "&1ebc37b92a8d488fb7e1b44594a340dd";

                        signatures = MD5Util.getMD5(original.toLowerCase()).toLowerCase();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String original = "&1ebc37b92a8d488fb7e1b44594a340dd";
                    signatures = MD5Util.getMD5(original.toLowerCase()).toLowerCase();
                }
            }
            /*Request authorised = originalRequest.newBuilder()
                    .addHeader("Youzy-Signature", signatures)
                    .addHeader("YouzyApp_Sign", NativeUtil.youzySign())
                    .addHeader("YouzyApp_ApiSign", NativeUtil.youzyApiSign())
                    .addHeader("YouzyApp_DataSign", NativeUtil.youzyDATASign())
                    .addHeader("YouzyApp_SuperSign", NativeUtil.youzySuperSign())
                    .addHeader("YouzyApp_FromSource", "Android-5.0")
                    .addHeader("YouzyApp_IP", CommonUtils.getLocalIpAddress())
                    .addHeader("WelcomeYouzyApi", "How much do you want Please call me at 13626686806")
                    .addHeader("YouzyMedia_Signature", NativeUtil.youzySignMedia())
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build();*/
            Request authorised = originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build();
            if (NetworkConstant.Switch.isPrintNetworkLog) {
                return response(chain.proceed(authorised));
            } else {
                return chain.proceed(authorised);
            }

        }
    };

    /**
     * Response处理
     *
     * @param response
     * @return
     */
    private static Response response(Response response) throws IOException {
        NetworkResponseEntity networkResponseInfo = new NetworkResponseEntity();
        networkResponseInfo.setSendTime(response.sentRequestAtMillis());
        networkResponseInfo.setReceiveTime(response.receivedResponseAtMillis());
        networkResponseInfo.setCode(response.code());
        networkResponseInfo.setMessage(response.message());
        networkResponseInfo.setRedirect(response.isRedirect());
        networkResponseInfo.setProtocol(response.protocol().toString());
        networkResponseInfo.setSuccessful(response.isSuccessful());
        networkResponseInfo.setUrl(response.request().url().toString());
        networkResponseInfo.setHttps(response.request().isHttps());
        networkResponseInfo.setMethod(response.request().method());

        if (response.headers() != null) {
            networkResponseInfo.setHeaders(response.headers().toString());
        } else {
            networkResponseInfo.setHeaders("");
        }
        if (response.request().body() != null) {
            try {
                Buffer buffer = new Buffer();
                response.request().body().writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = response.request().body().contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF_8);
                }
                networkResponseInfo.setSend(buffer.readString(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (response.body() != null) {
            String temp = response.body().string();
            networkResponseInfo.setContent(temp == null ? "" : temp);

        }
        networkResponseInfo.print("---", NetworkConstant.Switch.isJsonFormat);
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), networkResponseInfo.getContent()))
                .build();
    }
}
