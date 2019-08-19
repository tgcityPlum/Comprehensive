package com.tgcity.demo.testRefreshView;

import com.tgcity.common.bean.response.TestDataItemBean;
import com.tgcity.mvp.model.OnPublicRefreshViewCallBack;

import java.util.List;

public class TestRefreshViewConstant {

    public interface View extends OnPublicRefreshViewCallBack {
        void setList(List<TestDataItemBean> data);
    }

    public interface Presenter {
        void getList();
    }
}
