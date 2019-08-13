package com.tgcity.web;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;
import com.tgcity.web.dao.OnWebViewOperationCallBack;
import com.tgcity.web.enums.WebViewLayoutPosition;

/**
 * 定制的全局WebView
 */

@SuppressWarnings("ALL")
public class WebViewLayout extends LinearLayout {
    private AgentWeb agentWeb;
    private AgentWeb.IndicatorBuilder indicatorBuilder;
    private AgentWeb.AgentBuilder agentBuilder;
    private AgentWeb.CommonBuilder commonBuilder;
    private LinearLayout WebViewParent;
    private AgentWeb.PreAgentWeb preAgentWeb;
    //url
    private String mWebUrl;

    public WebViewLayout(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public WebViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public WebViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public WebViewLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setOrientation(LinearLayout.VERTICAL);
    }

    /**
     * 绑定Activity
     *
     * @param activity
     * @return
     */
    public WebViewLayout with(Activity activity) {
        agentBuilder = AgentWeb.with(activity);
        setConfig();
        return this;
    }

    /**
     * 绑定Fragment
     *
     * @param fragment
     * @return
     */
    public WebViewLayout with(Fragment fragment) {
        agentBuilder = AgentWeb.with(fragment);
        setConfig();
        return this;
    }

    /**
     * 定制布局与各种配置
     */
    protected void setConfig() {
        //设置截屏
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                android.webkit.WebView.enableSlowWholeDocumentDraw();
            } catch (AndroidRuntimeException e) {
                e.printStackTrace();
            }
        }*/
        //设置布局显示方式
        if (WebViewLayoutPosition.webView == WebViewManager.getInstance().getBuilder().getWebViewConfig().getWebViewLayoutPosition()) {
            indicatorBuilder = agentBuilder.setAgentWebParent((LinearLayout) this, new LayoutParams(-1, -1));//webview
        }
        if (WebViewLayoutPosition.head_webView == WebViewManager.getInstance().getBuilder().getWebViewConfig().getWebViewLayoutPosition() && WebViewManager.getInstance().getBuilder().getWebViewConfig().headView() != null) {
            if (WebViewManager.getInstance().getBuilder().getWebViewConfig().headView() != null){
                addView(WebViewManager.getInstance().getBuilder().getWebViewConfig().headView());//头部View
            }
            indicatorBuilder = agentBuilder.setAgentWebParent((LinearLayout) this, new LayoutParams(-1, -1));//webview
        }
        //设置基本属性
        if (WebViewManager.getInstance().getBuilder().getWebViewConfig().useProgressbar()) {
            commonBuilder = indicatorBuilder
                    .useDefaultIndicator(
                            WebViewManager.getInstance().getBuilder().getWebViewConfig().getProgressbarColor(),
                            WebViewManager.getInstance().getBuilder().getWebViewConfig().getProgressbarHeight());
        } else {
            commonBuilder = indicatorBuilder.closeIndicator();
        }
        preAgentWeb = commonBuilder.
                setWebViewClient(WebViewManager.getInstance().getBuilder().getWebViewConfig().getWebViewClient())
                .setWebChromeClient(WebViewManager.getInstance().getBuilder().getWebViewConfig().getWebChromeClient())
                .setPermissionInterceptor(WebViewManager.getInstance().getBuilder().getWebViewConfig().getPermissionInterceptor())
                .setSecurityType(WebViewManager.getInstance().getBuilder().getWebViewConfig().getSecurityType())
                .setAgentWebUIController(WebViewManager.getInstance().getBuilder().getWebViewConfig().getAgentWebUIController())
                .setMainFrameErrorView(WebViewManager.getInstance().getBuilder().getWebViewConfig().getMainFrameErrorView(), -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready();
        agentWeb = preAgentWeb.go(getWebUrl());
        //绑定操作回调
        WebViewManager.getInstance().getBuilder().getWebViewConfig().getOnWebViewBackOnClickCallBack(new OnWebViewOperationCallBack() {
            //是否可以返回
            @Override
            public boolean canGoBack() {
                return goBack();
            }

            //WebView截图
            @Override
            public Bitmap captureWebView(boolean allSize) {
                return WebViewLayout.this.captureWebView(allSize);
            }

            //截图处理完毕
            @Override
            public void createBitmapComplete() {
                setDrawingCacheEnabled(false);
            }

        });
    }

    /**
     * 获取 url
     */
    private String getWebUrl() {
        return mWebUrl;
    }

    /**
     * 设置 url
     */
    public WebViewLayout loadUrl(String url){
        this.mWebUrl = url;

        return this;
    }

    /**
     * 清理截图缓存
     *
     * @param enabled
     */
    public void setDrawingCacheEnabled(boolean enabled) {
        if (agentWeb != null) {
            agentWeb.getWebCreator().getWebView().setDrawingCacheEnabled(enabled);
        }
    }

    /*
    JS调用安卓本地
    androidInterface:自定义的一个与JS通信的类
    example:
    public class AndroidInterface {
        //方法与参数须提前与服务器商量好
        public void callAndroid(final String msg) {
        }
     }
     */
    public WebViewLayout javascriptCallAndroid(Object androidInterface) {
        agentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(true);
        agentWeb.getJsInterfaceHolder().addJavaObject("android", androidInterface);
        return this;
    }

    /**
     * 调用JS中的方法
     *
     * @param method 事先与JS商量好的方法名
     * @param params 参数，可空
     */
    public WebViewLayout androidCallJavascript(String method, String... params) {
        if (null == params || params.length == 0) {
            agentWeb.getJsAccessEntrace().quickCallJs(method);
        } else {
            agentWeb.getJsAccessEntrace().quickCallJs(method, params);
        }
        return this;
    }

    /**
     * 清除 WebView 缓存
     *
     * @param isCacheAll false 只清除WebView缓存  true清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
     */
    public WebViewLayout toCleanWebCache(boolean isCacheAll) {
        if (isCacheAll) {
            AgentWebConfig.clearDiskCache(getContext());
        } else {
            if (agentWeb != null) {
                //清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
                agentWeb.clearWebCache();
            }
        }
        return this;
    }

    /**
     * 打开网页
     *
     * @param url
     */
    public void show() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            agentWeb.getAgentWebSettings().getWebSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        agentWeb.getAgentWebSettings().getWebSettings().setDomStorageEnabled(true);//重点是这个设置设置适应Html5
        agentWeb.getUrlLoader().loadUrl(mWebUrl);
        /**
         修复嵌套在scrollview中webview报Unable to create layer for WebView, exceeds max size 8192而崩溃的情况，说白了就是禁用加速
         这个错误是由于webview在scrollview里设置成了wrap_content，导致webview不确定大小，而且webview启动了硬件加速，由于硬件加速是有个最小的缓存区域的，最终导致超过了缓存范围。
         解决方法：
         webView.setLayerType(View.LAYER_TYPE_NONE, null);
         或者将webview的父容器设置为match_parent
         */
        agentWeb.getWebCreator().getWebView().setLayerType(View.LAYER_TYPE_NONE, null);

    }

    /**
     * 重载网页
     */
    public void reload() {
        agentWeb.getUrlLoader().reload();
    }

    /**
     * 返回
     *
     * @return true：已经返回，false：到头了没法返回了
     */
    public boolean goBack() {
        return agentWeb == null ? false : agentWeb.back();
    }

    /**
     * 返回
     */
    public void onGoBack() {
        if (agentWeb != null && agentWeb.getWebCreator() != null && agentWeb.getWebCreator().getWebView() != null) {
            agentWeb.getWebCreator().getWebView().goBack();
        }
    }

    /**
     * 跟随Activity或Fragment生命周期调用
     */
    public void onResume() {
        if (agentWeb != null)
            agentWeb.getWebLifeCycle().onResume();
    }

    /**
     * 截取webView
     *
     * @param allSize 是否是全尺寸（整个网页）
     * @return
     */
    public Bitmap captureWebView(boolean allSize) {
        try {
            return allSize ? Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? captureWebViewLollipop() : captureWebViewKitKat() : capture();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 截取显示的页面
     *
     * @return
     */
    protected Bitmap capture() {
        agentWeb.getWebCreator().getWebView().setDrawingCacheEnabled(true);
        agentWeb.getWebCreator().getWebView().buildDrawingCache();
        return Bitmap.createBitmap(agentWeb.getWebCreator().getWebView().getDrawingCache());
    }

    /**
     * Android4.4以下版本
     * 对WebView进行截屏，虽然使用过期方法，但在当前Android版本中测试可行
     *
     * @param webView
     * @return
     */
    protected Bitmap captureWebViewKitKat() {
        Picture picture = agentWeb.getWebCreator().getWebView().capturePicture();
        int width = picture.getWidth();
        int height = picture.getHeight();
        if (width > 0 && height > 0) {
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            picture.draw(canvas);
            return bitmap;
        }
        return null;
    }

    /**
     * Android5.0及以上版本
     * 在Android5.0及以上版本，Android对WebView进行了优化，为了减少内存使用和提高性能，使用WebView加载网页时只绘制显示部分。如果我们不做处理，
     * 仍然使用上述代码截图的话，就会出现只截到屏幕内显示的WebView内容，其它部分是空白的情况。
     * 这时候，我们通过调用WebView.enableSlowWholeDocumentDraw()方法可以关闭这种优化，但要注意的是，该方法需要在WebView实例被创建前就要调用，
     * 否则没有效果。所以我们在WebView实例被创建前加入代码：
     * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
     * android.webkit.WebView.enableSlowWholeDocumentDraw();
     * }
     * 根据Google文档中描述，capturePicture()方法已不鼓励使用，推荐我们通过webView的onDraw(Canvas)去获取图像，所以这里我们去拿到网页的宽高后，
     * 就调用webView.draw(Canvas)方法生成webView截图。
     *
     * @param webView
     */
    protected Bitmap captureWebViewLollipop() {
        float scale = agentWeb.getWebCreator().getWebView().getScale();
        int width = agentWeb.getWebCreator().getWebView().getWidth();
        int height = (int) (agentWeb.getWebCreator().getWebView().getContentHeight() * scale + 0.5);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        agentWeb.getWebCreator().getWebView().draw(canvas);
        return bitmap;
    }

    /**
     * 跟随Activity或Fragment生命周期调用
     */
    public void onPause() {
        if (agentWeb != null)
            agentWeb.getWebLifeCycle().onPause();
    }

    public void clear() {
        if (agentWeb != null) {
            if (agentWeb.getWebCreator() != null) {
                if (agentWeb.getWebCreator().getWebView() != null) {
                    agentWeb.getWebCreator().getWebView().stopLoading();
                    if (agentWeb.getWebCreator().getWebView().getParent() != null) {
                        ((ViewGroup) agentWeb.getWebCreator().getWebView().getParent()).removeView(agentWeb.getWebCreator().getWebView());
                    }

                    if (agentWeb.getWebCreator().getWebView().getSettings() != null) {
                        agentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(false);
                    }
                    agentWeb.getWebCreator().getWebView().clearHistory();
                    agentWeb.getWebCreator().getWebView().clearCache(true);
//                    agentWeb.getWebCreator().getWebView().loadUrl(ConfigUtil.COCKPIT_URL);
                    agentWeb.getWebCreator().getWebView().freeMemory();
                    agentWeb.getWebCreator().getWebView().pauseTimers();
                    agentWeb.getWebCreator().getWebView().clearFormData();
                    agentWeb.getWebCreator().getWebView().clearMatches();
                    agentWeb.getWebCreator().getWebView().clearSslPreferences();
                    agentWeb.getWebCreator().getWebView().clearDisappearingChildren();
                    agentWeb.getWebCreator().getWebView().clearView();
                    agentWeb.getWebCreator().getWebView().clearAnimation();
                    agentWeb.getWebCreator().getWebView().removeAllViews();
                    agentWeb.getWebCreator().getWebView().destroy();
                }
//                if (agentWeb != null) {
//                    agentWeb.destroy();
//                }
            }
            indicatorBuilder = null;
            agentBuilder = null;
            commonBuilder = null;
            preAgentWeb = null;
            if (WebViewParent != null) {
                WebViewParent.removeAllViews();
            }
            WebViewParent = null;
            agentWeb = null;

        }
    }

    /**
     * 跟随Activity或Fragment生命周期调用
     */
    public void onDestroy() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onDestroy();
        }
    }

}
