package com.tgcity.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebUIControllerImplBase;
import com.just.agentweb.PermissionInterceptor;
import com.tgcity.base.widget.titlebar.TitleBar;
import com.tgcity.web.dao.OnWebViewOperationCallBack;
import com.tgcity.web.enums.WebViewLayoutPosition;

/**
 * WebView的业务定制
 */

@SuppressWarnings("ALL")
public class WebViewInit extends WebViewConfig {
    private String titleString = null;
    private boolean isInErrorState = false;//页面加载是否成功
    private TitleBar headview;//头部View
    private TextView title;//标题
    private Activity activity;//WebView所在的Activity
    private OnWebViewOperationCallBack onWebViewBackOnClickCallBack;//返回按钮点击回调
    private OnWebViewClientCallBack onWebViewClientCallBack;//webview回调
    private OnWebViewTitleCallBack onWebViewTitleCallBack;//标题显示回调


    public WebViewInit(Activity activity, TitleBar headview) {
        this.activity = activity;
        this.headview = headview;
        if (headview != null){
            title = headview.getTitleView();

            this.headview.setBackListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onWebViewBackOnClickCallBack != null) {
                        if (!onWebViewBackOnClickCallBack.canGoBack()) {
                            WebViewInit.this.activity.finish();
                            WebViewInit.this.activity = null;
                        }
                    }
                }
            });
        }
    }

    public WebViewInit setTitleString(String titleString) {
        this.titleString = titleString;
        return this;
    }

    /**
     * 头部View
     *
     * @return
     */
    @Override
    protected View headView() {
        if (headview != null && headview.getParent() != null) {
            ((ViewGroup) headview.getParent()).removeView(headview);
        }
        return headview;
    }


    /**
     * 是否使用进度条
     *
     * @return
     */
    @Override
    protected boolean useProgressbar() {
        return true;
    }

    /**
     * 进度条颜色
     *
     * @return
     */
    @Override
    protected int getProgressbarColor() {
        return activity.getResources().getColor(R.color.color_web_progress);
    }

    /**
     * 进度条高度
     *
     * @return
     */
    @Override
    protected int getProgressbarHeight() {
        return 3;
    }


    /**
     * 布局方式
     *
     * @return
     */
    @Override
    protected WebViewLayoutPosition getWebViewLayoutPosition() {
        return WebViewLayoutPosition.head_webView;
    }

    /**
     * webview控制器
     *
     * @return
     */
    @Nullable
    @Override
    protected WebViewClient getWebViewClient() {
        return new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("onPageStarted url=", url + "--");
                isInErrorState = false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("onPageFinished url=", url + "--");

                /*if (!TextUtils.isEmpty(url) && url.contains("about:blank")){
                    view.loadUrl(ConfigUtil.COCKPIT_URL);
                }else {
                    super.onPageFinished(view, url);
                }*/

                super.onPageFinished(view, url);
                if (!isInErrorState) {

                }

            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                isInErrorState = true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                isInErrorState = true;
                Log.e("onReceivedError" + error.getErrorCode(), error.getDescription().toString());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("shouldOverrideUrlLoading url=", url + "--");

                if (onWebViewClientCallBack != null) {
                    onWebViewClientCallBack.onShouldOverrideUrlLoading(view, url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.e("shouldOverrideUrlLoading url=", view.getUrl() + "--");
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //通过重写WebViewClient的onReceivedSslError方法来接受所有网站的证书，忽略SSL错误
                // 参考https://www.jianshu.com/p/8ef6340dc166
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }
        };
    }


    /**
     * 浏览器控制器
     *
     * @return
     */
    @Nullable
    @Override
    protected WebChromeClient getWebChromeClient() {
        return new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (onWebViewTitleCallBack != null) {
                    onWebViewTitleCallBack.onTitle(view.getTitle(), newProgress);
                }
                if (title != null) {
                    if (titleString != null) {
                        title.setText(titleString);
                    } else {
                        if (newProgress == 100) {
                            title.setText(view.getTitle());
                        } else {
                            title.setText("加载中...");
                        }
                    }


                }
            }
        };
    }


    /**
     * 权限拦截器
     *
     * @return
     */
    @Nullable
    @Override
    protected PermissionInterceptor getPermissionInterceptor() {
        return null;
    }

    /**
     * 安全检查模式
     *
     * @return
     */
    @Nullable
    @Override
    protected AgentWeb.SecurityType getSecurityType() {
        return AgentWeb.SecurityType.DEFAULT_CHECK;
    }

    /**
     * 定制相关弹窗页
     *
     * @return
     */
    @Override
    protected AgentWebUIControllerImplBase getAgentWebUIController() {
        return new AgentWebUIControllerImplBase();
    }

    /**
     * 出错View
     *
     * @return
     */
    @Nullable
    @Override
    protected int getMainFrameErrorView() {
        return R.layout.webview_error;
    }


    /**
     * 设置WebView控制回调
     *
     * @param onWebViewBackOnClickCallBack
     */
    @Override
    protected void getOnWebViewBackOnClickCallBack(OnWebViewOperationCallBack onWebViewBackOnClickCallBack) {
        this.onWebViewBackOnClickCallBack = onWebViewBackOnClickCallBack;
    }

    public WebViewInit setOnWebViewClientCallBack(OnWebViewClientCallBack onWebViewClientCallBack) {
        this.onWebViewClientCallBack = onWebViewClientCallBack;
        return this;
    }

    public WebViewInit setOnWebViewTitleCallBack(OnWebViewTitleCallBack onWebViewTitleCallBack) {
        this.onWebViewTitleCallBack = onWebViewTitleCallBack;
        return this;
    }

    public interface OnWebViewClientCallBack {
        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onPageFinished(WebView view, String url);

        void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse);

        void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);

        boolean onShouldOverrideUrlLoading(WebView view, String url);
    }

    public interface OnWebViewTitleCallBack {
        void onTitle(String title, int newProgress);
    }

}
