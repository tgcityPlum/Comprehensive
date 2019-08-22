package com.tgcity.mvp.view.activity;

import android.os.Bundle;

import com.tgcity.base.activity.BaseLauncherTimeActivity;
import com.tgcity.mvp.model.OnPresenterTaskCallBack;
import com.tgcity.mvp.present.CommonPresenter;


/**
 * MVP activity，如果使用MVP开发模式，可继承本类
 */

public abstract class MVPCommonActivity<V, P extends CommonPresenter<V>> extends BaseLauncherTimeActivity {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
            presenter.bindLifecycle(this.bindToLifecycle());
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) presenter.detachView();
        presenter = null;
        super.onDestroy();
    }

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    /**
     * 执行Presenter层的任务，这个方法出现的目的在于为了防止调用P层时出现空指针导致应用崩溃。
     * 举列：页面关闭后但网络未停止，当网络任务执行完毕后再调用presenter的时候出现空指针，
     * 故首先判空，将Presenter层的任务放到后面，以保证程序的绝对稳定
     */
    public void presenterTask(OnPresenterTaskCallBack<P> onPresenterTaskCallBack) {
        if (onPresenterTaskCallBack != null) {
            if (presenter != null) {
                onPresenterTaskCallBack.onPresenterTask(presenter);
            }
        }
    }

}
