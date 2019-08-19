package com.tgcity.mvp.model;

import com.tgcity.network.cache.model.ErrorMode;

/**
 * 列表下拉刷新上拉加载请求回调
 */

public interface OnDepositRequestPrepareListCallBack {

    void onRequestRefresh();

    void onRequestLoadMore();

    void onExceptionClick(ErrorMode errorMode);
}
