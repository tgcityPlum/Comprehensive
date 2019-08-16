package com.tgcity.mvp.present;

import com.tgcity.mvp.model.OnPublicRefreshViewCallBack;
import com.tgcity.network.cache.model.ErrorMode;
import com.tgcity.network.retrofit.ApiException;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * BasePresenter,自动绑定解绑View接口
 */

public abstract class BasePresenter<V> {
    public LifecycleTransformer lifecycleTransformer;
    public V view;
    private boolean isInit;//这个字段用于列表loading动画的触发，仅仅使用一次

    /**
     * 绑定生命周期
     *
     * @param bindToLifecycle
     */
    public void bindLifecycle(LifecycleTransformer bindToLifecycle) {
        lifecycleTransformer = bindToLifecycle;
    }

    /**
     * 绑定View接口
     *
     * @param v
     */
    public void attachView(V v) {
        view = v;
    }

    /**
     * 解绑View接口
     */
    public void detachView() {
        if (view != null) {
            view = null;
        }

    }

    /**
     * 统一处理错误信息或空信息
     *
     * @param e    异常实例
     * @param view View层回调
     */
    public void disposeErrorOrNull(Throwable e, OnPublicRefreshViewCallBack view) {
        disposeErrorOrNull(e, view, null, null);
    }

    /**
     * 手动处理空信息统一处理错误信息
     * @param e                        异常实例
     * @param view                     View层回调
     * @param onNullEventCallBack      空事件自定义处理回调
     */
    public void disposeErrorOrNull(Throwable e, OnPublicRefreshViewCallBack view, OnNullEventCallBack onNullEventCallBack) {
        disposeErrorOrNull(e, view, onNullEventCallBack, null);
    }

    /**
     * 处理空信息与错误信息
     *
     * @param e                        异常实例
     * @param view                     View层回调
     * @param onNullEventCallBack      空事件自定义处理回调
     * @param onExceptionEventCallBack 错误事件自定义处理回调
     */
    public void disposeErrorOrNull(Throwable e, OnPublicRefreshViewCallBack view, OnNullEventCallBack onNullEventCallBack, OnExceptionEventCallBack onExceptionEventCallBack) {
        if (viewNotNull()) {
            if (e instanceof ApiException) {
                if (((ApiException) e).errorMode.getErrorCode() == ErrorMode.SERVER_NULL.getErrorCode()) {
                    if (onNullEventCallBack != null) {
                        onNullEventCallBack.onNullEvent(e);
                    } else {
                        view.showEmpty();
                    }
                } else {
                    if (onExceptionEventCallBack != null) {
                        onExceptionEventCallBack.onExceptionEvent(e);
                    } else {
                        view.showError(e);
                    }
                }
            } else {
                if (onExceptionEventCallBack != null) {
                    onExceptionEventCallBack.onExceptionEvent(e);
                } else {
                    view.showError(e);
                }
            }
        }
    }

    /**
     * 处理空信息与错误信息
     *
     * @param e                        异常实例
     * @param onNullEventCallBack      空事件自定义处理回调
     * @param onExceptionEventCallBack 错误事件自定义处理回调
     */
    public void disposeErrorOrNull(Throwable e,  OnNullEventCallBack onNullEventCallBack, OnExceptionEventCallBack onExceptionEventCallBack) {
        if (viewNotNull()) {
            if (e instanceof ApiException) {
                if (((ApiException) e).errorMode.getErrorCode() == ErrorMode.SERVER_NULL.getErrorCode()) {
                    if (onNullEventCallBack != null) {
                        onNullEventCallBack.onNullEvent(e);
                    }
                } else {
                    if (onExceptionEventCallBack != null) {
                        onExceptionEventCallBack.onExceptionEvent(e);
                    }
                }
            } else {
                if (onExceptionEventCallBack != null) {
                    onExceptionEventCallBack.onExceptionEvent(e);
                }
            }
        }
    }

    /**
     * 统一处理错误信息或空信息
     * 本方法附带一个页码下标，专门为列表加载服务，逻辑如下：
     * 小于1时自动调用空回调；
     * 为1时且数据为空时，自动调用空回调；
     * 大于1时为正常列表加载后的处理，只需在onError回调中判断为true时调用列表停止加载方法方法即可，其余不做任何操作
     *
     * @param pagerIndex 页码
     * @param e          异常实例
     * @param view       View层回调
     * @return 如果返回为true，说明数据为空且页码不是首页
     */
    public boolean disposeErrorOrNull(int pagerIndex, Throwable e, OnPublicRefreshViewCallBack view) {
        if (viewNotNull()) {
            if (e instanceof ApiException) {
                if (((ApiException) e).errorMode.getErrorCode() == ErrorMode.SERVER_NULL.getErrorCode()) {
                    if (pagerIndex == 1) {
                        view.showEmpty();
                    } else {
                        view.showEmpty();
                        return true;
                    }
                } else {
                    view.showError(e);
                }
            } else {
                view.showError(e);
            }
        }
        return false;
    }

    /**
     * 判断视图是否为空
     */
    public boolean viewNotNull() {
        return view != null;
    }

    /**
     * 该P层是否初始化过
     */
    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    /**
     * 空事件自定义处理回调
     */
    public interface OnNullEventCallBack {

        void onNullEvent(Throwable e);
    }

    /**
     * 错误事件自定义处理回调
     */
    public interface OnExceptionEventCallBack {

        void onExceptionEvent(Throwable e);
    }
}
