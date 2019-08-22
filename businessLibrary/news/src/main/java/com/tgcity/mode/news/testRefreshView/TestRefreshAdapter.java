package com.tgcity.mode.news.testRefreshView;

import android.support.annotation.Nullable;

import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.mode.news.R;
import com.tgcity.refreshadapter.BaseListenerEventAdapter;
import com.tgcity.refreshadapter.BaseViewHolder;

import java.util.List;

public class TestRefreshAdapter extends BaseListenerEventAdapter<TestDataItemBean, BaseViewHolder> {

    public TestRefreshAdapter(int layoutResId, @Nullable List<TestDataItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestDataItemBean item) {
        helper.setText(R.id.tv_context, item.getAppName());
    }
}
