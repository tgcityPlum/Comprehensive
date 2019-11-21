package com.tgcity.mode.arouter.interfaces;

/**
 * @author TGCity
 * @date 2019/8/28
 * @describe    callback status with jumping by route
 */
public interface RouteNavigationCallBack {

    /**
     * Arrival Back
     */
    void onArrivalBack();

    /**
     * Lost Back
     */
    void onLostBack();

}
