package com.tgcity.demo.testRefreshView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tgcity.demo.R;
import com.tgcity.mvp.model.OnDepositRequestPrepareListCallBack;
import com.tgcity.mvp.view.activity.BaseMVPActivity;
import com.tgcity.mvp.widget.progress.ProgressView;
import com.tgcity.network.cache.model.ErrorMode;
import com.tgcity.refreshview.springview.widget.SpringView;
import com.tgcity.refreshview.utils.RefreshViewUtils;
import com.tgcity.resource.bean.response.TestDataItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestRefreshViewActivity extends BaseMVPActivity<TestRefreshViewConstant.View, TestRefreshViewPresenter> implements TestRefreshViewConstant.View {

    @BindView(R.id.progress)
    ProgressView progress;
    //上下拉刷新组件
    @BindView(R.id.refresh)
    SpringView refresh;
    //上下拉刷新组件
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    private TestRefreshAdapter refreshAdapter;
    //页码下标
    private int pageIndex = 1;

    @Override
    public int getViewLayout() {
        return R.layout.activity_test_refresh_view;
    }

    @Override
    public void initView() {
        refreshAdapter = new TestRefreshAdapter(R.layout.item_refresh, null);

        RefreshViewUtils.bindRecyclerViewWithRefreshAndAdapter(getContext(), new LinearLayoutManager(getContext()), recycleView, refresh, refreshAdapter, new OnDepositRequestPrepareListCallBack() {
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
    }

    @Override
    protected TestRefreshViewPresenter createPresenter() {
        return new TestRefreshViewPresenter();
    }

    @Override
    public String getCurrentPage() {
        return getString(R.string.test_refresh_activity);
    }

    @Override
    public void setList(List<TestDataItemBean> data) {
        List<TestDataItemBean> list = new ArrayList<>();

        if (data.size() > 10){
            for (int i = 0; i < 10; i++) {
                list.add(data.get(i));
            }
        }else {
            list.addAll(data);
        }
        //刷新列表数据
        RefreshViewUtils.depositRequestComplete(false, refresh, progress, refreshAdapter, pageIndex, list, this, p -> pageIndex = p);

    }

    @Override
    public void showProgress() {
        progress.showLoading();
    }

    @Override
    public void showError(Throwable e) {
        progress.errorOperation(e, new ProgressView.OnProgressViewCallBack() {

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
        progress.showEmpty(getResources().getDrawable(R.drawable.monkey_cry), "----", "---");
    }

    private void loadData() {
        presenterTask(TestRefreshViewPresenter::getList);
    }

}
