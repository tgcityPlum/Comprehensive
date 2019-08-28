package com.tgcity.base.network.retrofit;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.base.utils.LogUtils;
import com.tgcity.base.network.cache.model.ErrorMode;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


/**
 * @author TGCity
 * 异常处理类
 */
public class ApiException extends RuntimeException {
    public ErrorMode errorMode;

    /**
     * 请求错误
     *
     * @param errorMode 错误模型
     */
    public ApiException(ErrorMode errorMode) {
        super(errorMode.getErrorTitle());
        this.errorMode = errorMode;
        LogUtils.e("ApiException", "错误详情" + errorMode.getErrorTitle());
    }

    /**
     * 请求错误
     *
     * @param errorMode 错误模型
     */
    public ApiException(String message, ErrorMode errorMode) {
        super(message);
        this.errorMode = errorMode;
        LogUtils.e("ApiException", "错误详情" + message);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     * 错误代码：
     * Error 0
     * Ok 1
     * InternalServerError 100
     * SystemClose 200
     * SignatureFailed  300
     * Information 1001
     * Warning 1002
     */
    public static ApiException handleException(HttpResult httpResult) {
        ApiException apiException;

        if (ErrorMode.SERVER_NULL.getErrorCode() == httpResult.getCode()) {
            apiException = new ApiException(ErrorMode.SERVER_NULL.getErrorTitle(), ErrorMode.SERVER_NULL);
        } else {
            switch (httpResult.getCode()) {
                case 1001:
                    apiException = new ApiException(httpResult.getMessage(), ErrorMode.API_VISUALIZATION_MESSAGE.setErrorContent(httpResult.getMessage()));
                    break;
                case 1002:
                    apiException = new ApiException(httpResult.getMessage(), ErrorMode.API_VISUALIZATION_MESSAGE.setErrorContent(httpResult.getMessage()));
                    break;
                case 0:
                    apiException = new ApiException(httpResult.getMessage(), ErrorMode.API_NO_VISUALIZATION_MESSAGE);
                    break;
                case 300:
                    apiException = new ApiException(ErrorMode.SIGNATURE_FAILURE_TIME);
                    break;
                default:
                    apiException = new ApiException(httpResult.getMessage(), ErrorMode.HTTP_OTHER_ERROR);

            }
        }

        return apiException;

    }

    /**
     * 对请求错误进行统一处理
     *
     * @param e 错误
     * @return ApiException
     */
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            ex = new ApiException(e.getMessage(), ErrorMode.HTTP_OTHER_ERROR);
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            //数据解析出错
            ex = new ApiException(e.getMessage(), ErrorMode.DATA_FORMAT_ERROR);
            return ex;
        } else if (e instanceof ClassCastException) {
            //类型转换错误
            ex = new ApiException(e.getMessage(), ErrorMode.TYPE_CAST_ERROR);
            return ex;
        } else if (e instanceof ConnectException) {
            //连接失败
            ex = new ApiException(ErrorMode.CONNECT_TIME_OUT);
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            //证书验证失败
            ex = new ApiException(ErrorMode.SIGNATURE_FAILURE_SSL);
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            //连接超时
            ex = new ApiException(ErrorMode.CONNECT_TIME_OUT);
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ApiException(ErrorMode.CONNECT_TIME_OUT);
            return ex;
        } else if (e instanceof UnknownHostException) {
            //无法解析该域名
            ex = new ApiException(ErrorMode.UNKNOWN_HOST);
            return ex;
        } else {
            ex = new ApiException(e.getMessage(), ErrorMode.HTTP_OTHER_ERROR);
            return ex;
        }
    }
}

