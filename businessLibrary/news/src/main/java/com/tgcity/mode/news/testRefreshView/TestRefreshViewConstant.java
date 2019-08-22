package com.tgcity.mode.news.testRefreshView;

import com.tgcity.base.mvp.model.OnPublicRefreshViewCallBack;
import com.tgcity.base.network.bean.response.TestDataItemBean;

import java.util.List;

public class TestRefreshViewConstant {

    public interface View extends OnPublicRefreshViewCallBack {
        void setList(List<TestDataItemBean> data);
    }

    public interface Presenter {
        void getList();
    }
}
