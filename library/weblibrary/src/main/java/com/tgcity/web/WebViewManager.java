package com.tgcity.web;

/**
 * WebView参数构造
 */

public class WebViewManager {

    private Builder builder;

    private static WebViewManager instance;

    public static WebViewManager getInstance() {
        if (instance == null) {
            instance = new WebViewManager();
        }
        return instance;
    }

    public void build(Builder builder) {
        this.builder = builder;
    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder {
        private WebViewConfig webViewConfig;

        public WebViewConfig getWebViewConfig() {
            return webViewConfig;
        }

        public Builder setWebViewConfig(WebViewConfig webViewConfig) {
            this.webViewConfig = webViewConfig;
            return this;
        }
    }
}
