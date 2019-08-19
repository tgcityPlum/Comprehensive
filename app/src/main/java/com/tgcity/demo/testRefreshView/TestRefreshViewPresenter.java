package com.tgcity.demo.testRefreshView;

import android.util.Log;

import com.tgcity.mvp.present.BasePresenter;
import com.tgcity.network.bean.result.HttpResult;
import com.tgcity.network.callback.SimpleCallBack;
import com.tgcity.network.retrofit.NetworkRetrofitUtils;
import com.tgcity.resource.bean.response.TestDataItemBean;

import java.util.List;

public class TestRefreshViewPresenter extends BasePresenter<TestRefreshViewConstant.View> implements TestRefreshViewConstant.Presenter{

    @Override
    public void getList() {
        if (viewNotNull()) {
            view.showProgress();
        }

        String token = "3c7fcf8c-1ec5-4865-b369-7c1847caa84b";
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
                    view.setList(result.getResults());
                }
            }
        });
    }
}
