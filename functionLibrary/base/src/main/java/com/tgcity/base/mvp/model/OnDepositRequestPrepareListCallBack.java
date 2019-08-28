package com.tgcity.base.mvp.model;

import com.tgcity.base.network.cache.model.ErrorMode;

/**
 * @author TGCity
 * 列表下拉刷新上拉加载请求回调
 */

public interface OnDepositRequestPrepareListCallBack {

    /**
     * recyclerView refresh
     */
    void onRequestRefresh();

    /**
     * recyclerView load more
     */
    void onRequestLoadMore();

    /**
     * exception click
     * @param errorMode error mode code
     */
    void onExceptionClick(ErrorMode errorMode);
}
