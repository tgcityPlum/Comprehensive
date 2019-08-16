package com.tgcity.demo.testmvp;

import android.view.View;
import android.widget.Button;

import com.tgcity.demo.R;
import com.tgcity.mvp.model.OnPresenterTaskCallBack;
import com.tgcity.mvp.view.activity.BaseMVPActivity;
import com.tgcity.mvp.widget.progress.ProgressView;
import com.tgcity.network.cache.model.ErrorMode;

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
        btnTestData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterTask(new OnPresenterTaskCallBack<TestMVPPresenter>() {
                    @Override
                    public void onPresenterTask(TestMVPPresenter presenter) {
                        presenter.onShow();
                    }
                });
            }
        });
    }

    private void onLoadData() {

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
