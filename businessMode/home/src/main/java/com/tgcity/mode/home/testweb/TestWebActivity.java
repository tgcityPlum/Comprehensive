package com.tgcity.mode.home.testweb;

import android.view.KeyEvent;
import android.widget.Toast;

import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.widget.titlebar.TitleBar;
import com.tgcity.mode.home.R;
import com.tgcity.web.WebViewInit;
import com.tgcity.web.WebViewLayout;
import com.tgcity.web.WebViewManager;

/**
 * 测试web界面的显示
 */
public class TestWebActivity extends BaseCommonActivity {

    WebViewLayout webLayout;
    TitleBar titleBar;

    //web显示的标题
    private String title;
    //第一次点击时间
    private long firstTime = 0;

    @Override
    public String getCurrentPage() {
        return getString(R.string.test_web_activity);
    }

    @Override
    public int getViewLayout() {
        return R.layout.activity_test_web;
    }

    @Override
    public void initView() {
        webLayout = findViewById(R.id.web_layout);
        titleBar = findViewById(R.id.titleBar);
        onInitWeb();
    }

    /**
     * 配置web环境
     */
    private void onInitWeb() {
        //初始化WebViewManager
        WebViewManager.getInstance()
                .build(new WebViewManager.Builder()
                        .setWebViewConfig(new WebViewInit(this, titleBar).setOnWebViewTitleCallBack(new WebViewInit.OnWebViewTitleCallBack() {
                            @Override
                            public void onTitle(String title, int newProgress) {
                                if (title != null && !"加载中...".equals(title)) {
                                    TestWebActivity.this.title = title;
                                } else {
                                    TestWebActivity.this.title = getString(R.string.app_name);
                                }
                            }
                        }).setTitleString(title)));
        //显示url
        webLayout.loadUrl("http://113.143.100.152:18848/gov_dashboard_usl").with(this).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webLayout.goBack()) {
                webLayout.onGoBack();
            } else {
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime < 2000) {
                    System.exit(0);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.exit_app), Toast.LENGTH_SHORT).show();
                    firstTime = System.currentTimeMillis();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }
}
