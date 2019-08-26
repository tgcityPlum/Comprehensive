package com.tgcity.mode.news.mvp;

import com.tgcity.mvp.view.activity.MVPCommonActivity;

/**
 * MVPPlugin
 * MVPBaseActivity核心是继承 MVP library中 MVPCommonActivity 类
 * 只是起到一个中转作用
 * 单纯是为了适应 MVPPlugin插件
 */

public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends MVPCommonActivity<V,T>{

}
