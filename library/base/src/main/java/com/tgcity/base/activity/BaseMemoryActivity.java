package com.tgcity.base.activity;

import com.bumptech.glide.Glide;
import com.tgcity.base.R;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 基础的activity类
 * --监听系统内存的状况
 * ----参考地址：https://mp.weixin.qq.com/s?__biz=MzIxNjc0ODExMA==&mid=2247484311&idx=1&sn=1fe0416bed4137dd45c6e9c153bb14f4&chksm=97851ab6a0f293a0cde28ff6d1091b2232e1758e9845a05549d01c62f412def742985d642630&scene=21#wechat_redirect
 */
public abstract class BaseMemoryActivity extends BaseLoadingActivity {

    /**
     * onTrimMemory
     *
     * @param level level flag
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        switch (level) {
            case TRIM_MEMORY_RUNNING_MODERATE:
                onTrimMemoryRunningModerate();
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                onTrimMemoryRunningLow();
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
                onTrimMemoryRunningCritical();
                break;
            case TRIM_MEMORY_UI_HIDDEN:
                onTrimMemoryUIHidden();
                break;
            default:
                break;
        }

        Glide.get(this).trimMemory(level);
    }

    /**
     * Level for {@link #onTrimMemory(int)}: the process is not an expendable
     * background process, but the device is running moderately low on memory.
     * Your running process may want to release some unneeded resources for
     * use elsewhere.
     */
    private void onTrimMemoryRunningModerate() {
        //可以适当地清理一些垃圾了
        logBaseMemoryActivity(getString(R.string.base_memory_activity_running_moderate));
    }

    /**
     * Level for {@link #onTrimMemory(int)}: the process is not an expendable
     * background process, but the device is running low on memory.
     * Your running process should free up unneeded resources to allow that
     * memory to be used elsewhere.
     */
    private void onTrimMemoryRunningLow() {
        //强烈建议开始清理垃圾
        logBaseMemoryActivity(getString(R.string.base_memory_activity_running_low));
    }

    /**
     * Level for {@link #onTrimMemory(int)}: the process is not an expendable
     * background process, but the device is running extremely low on memory
     * and is about to not be able to keep any background processes running.
     * Your running process should free up as many non-critical resources as it
     * can to allow that memory to be used elsewhere.  The next thing that
     * will happen after this is {@link #onLowMemory()} called to report that
     * nothing at all can be kept in the background, a situation that can start
     * to notably impact the user.
     */
    private void onTrimMemoryRunningCritical() {
        //如果不清理垃圾那就GAME OVER了
        logBaseMemoryActivity(getString(R.string.base_memory_activity_running_critical));
    }

    /**
     * Level for {@link #onTrimMemory(int)}: the process had been showing
     * a user interface, and is no longer doing so.  Large allocations with
     * the UI should be released at this point to allow memory to be better
     * managed.
     */
    private void onTrimMemoryUIHidden() {
        //可以清理一些用户不需要看到但又不重要，显示之后又可以复原的东西
        logBaseMemoryActivity(getString(R.string.base_memory_activity_ui_hidden));
    }

    public abstract String getCurrentPage();

    public String getCurrentPageName(String message) {
        return getString(R.string.base_memory_activity_current_page_name, getCurrentPage(), getLocalClassName(), message);
    }

    /**
     * 输出当前界面调用方法的日志
     */
    private void logBaseMemoryActivity(String message) {
        if (BaseConstant.Power.isBaseMemoryActivityLogShow) {
            LogUtils.d(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Glide clearMemory(must in main thread)
        Glide.get(this).clearMemory();
        //Glide clearDiskCache(must in child thread)
        // TODO: 2019/6/28 待测试
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Glide.get(BaseMemoryActivity.this).clearDiskCache();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
