package com.tgcity.base.mvp.model;

/**
 * @author TGCity
 * 通用的列表加载动作回调
 */

public interface OnPublicRefreshViewCallBack {
    /**
     * 显示加载页
     */
    void showProgress();

    /**
     * 显示加载失败
     * @param e throwable
     */
    void showError(Throwable e);

    /**
     * 显示空布局
     */
    void showEmpty();
}
