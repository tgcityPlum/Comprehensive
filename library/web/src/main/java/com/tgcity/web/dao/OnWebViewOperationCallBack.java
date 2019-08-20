package com.tgcity.web.dao;

import android.graphics.Bitmap;

/**
 * WebView控制接口，需要什么功能可以在此定制
 */

public interface OnWebViewOperationCallBack {
    /**
     * 用于自定义头部，控制WebView的返回
     *
     * @return true:还可以返回，不能关闭页面；false：不能返回了，可以关闭页面
     */
    boolean canGoBack();

    /**
     * 网页截图
     *
     * @param allSize 是否截取整个网页（包括未显示的部分）
     * @return 截取到的bitmap
     */
    Bitmap captureWebView(boolean allSize);

    /**
     * 截图完成之后的回调
     * 推荐在设置在captureWebView之后，且强烈建议必调，因为完成之后会调用一个setDrawingCacheEnabled(false)清除内存缓存
     */
    void createBitmapComplete();

}
