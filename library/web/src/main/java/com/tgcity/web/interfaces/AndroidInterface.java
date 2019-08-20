package com.tgcity.web.interfaces;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

/**
 * JS接口
 */

public class AndroidInterface {
    private Handler deliver;

    public AndroidInterface() {
        if (deliver == null) {
            deliver = new Handler(Looper.getMainLooper());
        }
    }

    public void clear() {
        if (deliver != null) {
            deliver.removeCallbacksAndMessages(null);
        }
        deliver = null;
    }

    @JavascriptInterface
    public void toAndroidInfo() {
        deliver.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}
