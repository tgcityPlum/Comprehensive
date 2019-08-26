package com.tgcity.mode.news.indexlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tgcity.base.mvp.model.OnDepositRequestPrepareListCallBack;
import com.tgcity.base.network.bean.response.TestDataItemBean;
import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.base.widget.progress.ProgressView;
import com.tgcity.base.widget.titlebar.TitleBar;
import com.tgcity.mode.news.R;
import com.tgcity.mvp.view.activity.MVPCommonActivity;
import com.tgcity.refreshview.springview.widget.SpringView;
import com.tgcity.refreshview.utils.RefreshViewUtils;

import java.util.ArrayList;
import java.util.List;


public class NewsIndexListActivity extends MVPCommonActivity<NewsIndexListConstant.View, NewsIndexListPresenter> implements NewsIndexListConstant.View {

//    @BindView(R2.id.titleBar2)
    public TitleBar titleBar;

    ProgressView progressView;
    //上下拉刷新组件
    SpringView springView;
    //上下拉刷新组件
    RecyclerView recycleView;

    private NewsIndexListAdapter refreshAdapter;
    //页码下标
    private int pageIndex = 1;

    @Override
    public int getViewLayout() {
        return R.layout.activity_news_index_list;
    }

    @Override
    public void initView() {
        titleBar = findViewById(R.id.titleBar2);
        progressView = findViewById(R.id.progressView);
        springView = findViewById(R.id.springView);
        recycleView = findViewById(R.id.recycleView);

        refreshAdapter = new NewsIndexListAdapter(R.layout.item_refresh, null);

        RefreshViewUtils.bindRecyclerViewWithRefreshAndAdapter(getContext(), new LinearLayoutManager(getContext()), recycleView, springView, refreshAdapter, new OnDepositRequestPrepareListCallBack() {
            @Override
            public void onRequestRefresh() {
                pageIndex = 1;
                loadData();
            }

            @Override
            public void onRequestLoadMore() {
                loadData();
            }

            @Override
            public void onExceptionClick(ErrorMode errorMode) {

            }
        });

        loadData();

        titleBar.setBackListener(view -> finish());
    }


    @Override
    protected NewsIndexListPresenter createPresenter() {
        return new NewsIndexListPresenter();
    }

    @Override
    public String getCurrentPage() {
        return getString(R.string.test_refresh_activity);
    }

    @Override
    public void setList(List<TestDataItemBean> data) {
        List<TestDataItemBean> list = new ArrayList<>();

        if (data.size() > 10) {
            for (int i = 0; i < 10; i++) {
                list.add(data.get(i));
            }
        } else {
            list.addAll(data);
        }
        //刷新列表数据
        RefreshViewUtils.depositRequestComplete(false, springView, progressView, refreshAdapter, pageIndex, list, this, p -> pageIndex = p);

    }

    @Override
    public void showProgress() {
        progressView.showLoading();
    }

    @Override
    public void showError(Throwable e) {
        progressView.errorOperation(e, new ProgressView.OnProgressViewCallBack() {

            @Override
            public void onReTry() {
                loadData();
            }

            @Override
            public void onOther(ErrorMode errorMode) {
                loadData();
            }
        });
    }

    @Override
    public void showEmpty() {
        progressView.showEmpty(getResources().getDrawable(R.drawable.monkey_cry), "----", "---");
    }

    private void loadData() {
        presenterTask(NewsIndexListPresenter::getList);
    }

}
