package com.tgcity.demo.testmvp;

import com.tgcity.base.mvp.model.OnPublicRefreshViewCallBack;

public class TestMVPConstact {

    public interface View extends OnPublicRefreshViewCallBack{
        void onShowData();
    }

    public interface Presenter{
        void onShow();
    }

}
