package com.tgcity.mode.news.mvp;

import com.tgcity.mvp.present.CommonPresenter;

/**
 * MVPPlugin
 * BasePresenterImpl核心是继承CommonPresenter这个MVP library中常用的Presenter类
 * 只是起到一个中转作用
 * 单纯是为了适应 MVPPlugin插件
 */

public abstract class BasePresenterImpl<V extends BaseView> extends CommonPresenter<V> {

}
