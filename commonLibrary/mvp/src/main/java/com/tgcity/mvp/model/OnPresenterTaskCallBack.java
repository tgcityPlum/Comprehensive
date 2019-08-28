package com.tgcity.mvp.model;

/**
 * @author TGCity
 * 执行Presenter层的任务
 */

public interface OnPresenterTaskCallBack<P> {
    /**
     * presenter task
     * @param presenter P
     */
    void onPresenterTask(P presenter);
}
