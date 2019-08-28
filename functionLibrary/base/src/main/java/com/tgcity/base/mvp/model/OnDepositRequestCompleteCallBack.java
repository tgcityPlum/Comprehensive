package com.tgcity.base.mvp.model;

/**
 * @author TGCity
 * 请求成功后的页码回调
 */

public interface OnDepositRequestCompleteCallBack {

    /**
     * recyclerView page change
     * @param p the page index
     */
    void onPageIndexChanged(int p);
}
