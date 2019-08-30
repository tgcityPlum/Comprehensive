package com.tgcity.mode.web.index;

import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.widget.titlebar.TitleBar;
import com.tgcity.mode.web.R;
import com.tgcity.web.WebViewInit;
import com.tgcity.web.WebViewLayout;
import com.tgcity.web.WebViewManager;

/**
 * @author TGCity
 * 测试web界面的显示
 */
@Route(path = RouteConstant.WebMode.MAIN_FRAGMENT)
public class WebCommonActivity extends BaseCommonActivity {

    WebViewLayout webLayout;
    TitleBar titleBar;

    /**
     * web显示的标题
     */
    private String title;

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

    @Override
    public int getViewLayout() {
        return R.layout.web_activity_common;
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
                                    WebCommonActivity.this.title = title;
                                } else {
                                    WebCommonActivity.this.title = getString(R.string.app_name);
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
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
