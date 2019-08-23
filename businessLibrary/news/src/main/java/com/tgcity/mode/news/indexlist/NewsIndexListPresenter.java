package com.tgcity.mode.news.indexlist;

import android.util.Log;

import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.mode.news.net.NewsRetrofitUtils;
import com.tgcity.mvp.present.CommonPresenter;
import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.network.callback.SimpleCallBack;

import java.util.List;

public class NewsIndexListPresenter extends CommonPresenter<NewsIndexListConstant.View> implements NewsIndexListConstant.Presenter{

    @Override
    public void getList() {
        if (viewNotNull()) {
            view.showProgress();
        }

        String token = "3c7fcf8c-1ec5-4865-b369-7c1847caa84b";
        NewsRetrofitUtils.getInstance().getTestList(token, new SimpleCallBack<HttpResult<List<TestDataItemBean>>>() {
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
