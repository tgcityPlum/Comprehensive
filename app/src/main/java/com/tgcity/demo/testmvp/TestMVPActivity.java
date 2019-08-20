package com.tgcity.demo.testmvp;

import android.widget.Button;

import com.tgcity.base.widget.progress.ProgressView;
import com.tgcity.demo.R;
import com.tgcity.mvp.view.activity.BaseMVPActivity;
import com.tgcity.base.network.cache.model.ErrorMode;

import butterknife.BindView;

public class TestMVPActivity extends BaseMVPActivity<TestMVPConstact.View, TestMVPPresenter> implements TestMVPConstact.View {

    @BindView(R.id.testData)
    Button btnTestData;

    @BindView(R.id.progress)
    ProgressView progress;

    @Override
    public int getViewLayout() {
        return R.layout.activity_test_mvp;
    }

    @Override
    public void initView() {
        btnTestData.setOnClickListener(view -> presenterTask(TestMVPPresenter::onShow));
    }

    @Override
    protected TestMVPPresenter createPresenter() {
        return new TestMVPPresenter();
    }

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

    @Override
    public void onShowData() {
        progress.showContent();
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

            }

            @Override
            public void onOther(ErrorMode errorMode) {

            }
        });
    }

    @Override
    public void showEmpty() {
        progress.showEmpty(getResources().getDrawable(R.drawable.monkey_cry), "----", "---");
    }

}
