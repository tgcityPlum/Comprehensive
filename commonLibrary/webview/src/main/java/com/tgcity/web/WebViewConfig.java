package com.tgcity.web;

import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebUIControllerImplBase;
import com.just.agentweb.PermissionInterceptor;
import com.tgcity.web.dao.OnWebViewOperationCallBack;
import com.tgcity.web.enums.WebViewLayoutPosition;

public abstract class WebViewConfig {

    /**
     * 设置头部
     */
    @Nullable
    protected abstract View headView();


    /**
     * 是否使用进度条
     */
    protected abstract boolean useProgressbar();

    /**
     * 进度条颜色
     */
    protected abstract int getProgressbarColor();

    /**
     * 进度条高度
     */
    protected abstract int getProgressbarHeight();

    /**
     * 布局方式
     */
    @Nullable
    protected abstract WebViewLayoutPosition getWebViewLayoutPosition();

    /**
     * webview控制器
     */
    @Nullable
    protected abstract WebViewClient getWebViewClient();

    /**
     * 浏览器控制器
     */
    @Nullable
    protected abstract WebChromeClient getWebChromeClient();

    /**
     * 权限拦截器
     */
    @Nullable
    protected abstract PermissionInterceptor getPermissionInterceptor();

    /**
     * 安全检查模式
     */
    @Nullable
    protected abstract AgentWeb.SecurityType getSecurityType();

    /**
     * 定制相关弹窗页
     */
    @Nullable
    protected abstract AgentWebUIControllerImplBase getAgentWebUIController();

    /**
     * 出错View
     */
    protected abstract int getMainFrameErrorView();

    /**
     * 设置WebView控制回调
     */
    protected abstract void getOnWebViewBackOnClickCallBack(OnWebViewOperationCallBack onWebViewBackOnClickCallBack);

}
