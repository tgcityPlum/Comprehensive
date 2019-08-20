package com.tgcity.base.mvp.utils;

import android.content.Intent;
import android.os.Looper;

import com.tgcity.base.application.BaseApplication;
import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.base.network.retrofit.ApiException;
import com.tgcity.base.utils.IntentUtils;
import com.tgcity.base.utils.ToastUtils;

/**
 * error工具类,用于区分是那种错误类型的操作并提供相应的回调给调用者
 * Created by Administrator on 2018/7/24.
 */

public class ErrorEventOperation {


    /**
     * 判断是否为ApiException
     *
     * @param throwable
     * @return
     */
    public static ApiException deposit(Throwable throwable) {
        return throwable instanceof ApiException ? (ApiException) throwable : null;
    }

    /**
     * 拿到ErrorMode
     *
     * @param throwable
     * @return
     */
    public static ErrorMode depositReturnErrorMode(Throwable throwable) {
        return throwable instanceof ApiException ? ((ApiException) throwable).errorMode : null;
    }

    /**********************************各种错误处理************************************************/
    public static void operation(Throwable throwable) {
        operation(false, depositReturnErrorMode(throwable));

    }

    public static void operation(ErrorMode errorMode) {
        operation(false, errorMode);

    }

    public static void operation(boolean isShowErrorMsg, Throwable throwable) {
        operation(isShowErrorMsg, depositReturnErrorMode(throwable));

    }

    public static void operation(Throwable throwable, onErrorEventOperationCallBack onErrorEventOperationCallBack) {
        operation(depositReturnErrorMode(throwable), onErrorEventOperationCallBack);

    }

    public static void operation(boolean isShowErrorMsg, ErrorMode errorMode) {
        if (errorMode == null) {
            return;
        }
        if (isShowErrorMsg) {
            if (Looper.getMainLooper() == Looper.getMainLooper() && ErrorMode.API_VISUALIZATION_MESSAGE == errorMode) {
                ToastUtils.showShortToast(BaseApplication.getInstances(), errorMode.getErrorTitle());
            }
        }
        operation(errorMode, null);
    }

    /**
     * 错误处理
     *
     * @param errorMode
     * @param onErrorEventOperationCallBack 暴露一个接口给调用者手动处理，如果为空，则视为自动处理
     */
    public static void operation(ErrorMode errorMode, onErrorEventOperationCallBack onErrorEventOperationCallBack) {
        if (errorMode == null) {
            return;
        }
        if (ErrorMode.NO_AUTHORITY == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onNoAuthority();
            }

        }else if (ErrorMode.API_VISUALIZATION_MESSAGE == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onVisualizationMsg(errorMode.getErrorTitle());
            }

        } else if (ErrorMode.OVERLOAD == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onOverLoad();
            }
        } else if (ErrorMode.NO_CACHE == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onOther(errorMode);
            }
        } else if (ErrorMode.NO_NETWORK == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onNoNetWork();
            } else {
                BaseApplication.getInstances().startActivity(IntentUtils.getNetworkIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        } else if (ErrorMode.SINGNATURE_FAILURE_TIME == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onSingnatureFailTime();
            } else {
                BaseApplication.getInstances().startActivity(IntentUtils.getDateIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }

        } else if (ErrorMode.SINGNATURE_FAILURE_SSL == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onOther(errorMode);
            }
        } else if (ErrorMode.CONNECT_TIME_OUT == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onTimeOut();
            }
        } else if (ErrorMode.UNKNOWN_HOST == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onOther(errorMode);
            }
        } else if (ErrorMode.DATA_FORMATE_ERROR == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onDataFormateError();
            }
        } else if (ErrorMode.TYPE_CAST_ERROR == errorMode) {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onCastError();
            }
        } else {
            if (onErrorEventOperationCallBack != null) {
                onErrorEventOperationCallBack.onOther(errorMode);
            }
        }
    }

    /**
     * 错误处理回调
     */
    public interface onErrorEventOperationCallBack {

        void onNoAuthority();

        void onVisualizationMsg(String msg);

        void onOverLoad();

        void onNoNetWork();

        void onSingnatureFailTime();

        void onTimeOut();

        void onOther(ErrorMode errorMode1);

        void onDataFormateError();

        void onCastError();
    }
}
