package com.tgcity.mode.news.mvp;

import com.tgcity.base.mvp.model.OnPublicRefreshViewCallBack;

/**
 * MVPPlugin
 * BaseView核心是继承 OnPublicRefreshViewCallBack 接口
 * 只是起到一个中转作用
 * 单纯是为了适应 MVPPlugin插件
 */

public interface BaseView extends OnPublicRefreshViewCallBack {

}
