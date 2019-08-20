package com.tgcity.demo.testmvp;

import android.util.Log;

import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.mvp.present.BasePresenter;
import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.network.callback.SimpleCallBack;
import com.tgcity.network.retrofit.NetworkRetrofitUtils;

import java.util.List;

public class TestMVPPresenter extends BasePresenter<TestMVPConstact.View> implements TestMVPConstact.Presenter {

    @Override
    public void onShow() {
        if (viewNotNull()) {
            view.showProgress();
        }

        String token = "33b52682-5b13-4811-965f-7889d754f3f9";
        NetworkRetrofitUtils.getInstance().getTestList(token, new SimpleCallBack<HttpResult<List<TestDataItemBean>>>() {
            @Override
            public void onError(Throwable e) {
                Log.e("TestMVPPresenter", "onError");
                disposeErrorOrNull(e, view);
            }

            @Override
            public void onNext(HttpResult<List<TestDataItemBean>> result) {
                Log.e("TestMVPPresenter", "onNext");

                if (viewNotNull()) {
                    view.onShowData();
                }
            }
        });
    }
}
