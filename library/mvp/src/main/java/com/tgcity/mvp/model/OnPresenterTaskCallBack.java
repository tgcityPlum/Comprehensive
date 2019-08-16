package com.tgcity.mvp.model;

/**
 * 执行Presenter层的任务
 */

public interface OnPresenterTaskCallBack<P> {
    void onPresenterTask(P presenter);
}
