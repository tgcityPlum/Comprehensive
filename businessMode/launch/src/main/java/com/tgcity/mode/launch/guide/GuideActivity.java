package com.tgcity.mode.launch.guide;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.interfaces.RouteActivityNavigationCallBack;
import com.tgcity.base.utils.RouteActivityUtils;
import com.tgcity.mode.launch.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author TGCity
 * launch模块--启动界面
 */
@Route(path = RouteConstant.LaunchMode.MAIN_FRAGMENT)
public class GuideActivity extends BaseCommonActivity {

    /**
     * Gif动画
     */
    private GifImageView gifView;

    /**
     * 倒计时控件
     */
    private TextView tvTime;

    /**
     * activity jump delay millis
     *
     * @return long
     */
    private long delayMillis = 1000;

    /**
     * the time of countdown
     */
    private int countdownTime = 3;

    /**
     * jump activity type
     * 0: jump by gif time
     * 1: jump by countdown time
     */
    private int jumpActivityType = BaseConstant.ItemType.LEVEL_0;

    /**
     * jump click type
     * true: has click jump
     * false: no click jump or jump failed
     */
    private boolean jumpClickType = false;

    @Override
    public int getViewLayout() {
        return R.layout.launch_activity_guide;
    }

    @Override
    public void initView() {
        gifView = findViewById(R.id.gifView);
        tvTime = findViewById(R.id.tv_time);

        gifView.setImageResource(R.drawable.guide_bg);

        tvTime.setOnClickListener(view -> toJumpActivity());
    }

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (jumpActivityType == BaseConstant.ItemType.LEVEL_0) {
            jumpByGifTime();
        } else if (jumpActivityType == BaseConstant.ItemType.LEVEL_1) {
            tvTime.setVisibility(View.VISIBLE);
            jumpByCountdownTime();
        }
    }

    private void jumpByCountdownTime() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                //countdownTime + 1 : 倒计时到0结束；  countdownTime：倒计时到1结束
                .take(countdownTime + 1)
                //倒计时的时间处理
                .map(aLong -> countdownTime - aLong)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                //倒计时开始前触发的方法
                .doOnSubscribe(disposable -> {

                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvTime.setText(getString(R.string.guide_countdown_time, aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        toJumpActivity();
                    }
                });
    }

    private void jumpByGifTime() {
        //1秒后动画结束后执行相关操作
        Handler handler = new Handler();
        //界面跳转
        handler.postDelayed(this::toJumpActivity, delayMillis);
    }

    /**
     * 界面跳转,判断是跳转登录还是跳转主页
     */
    private void toJumpActivity() {
        if (jumpClickType) {
            return;
        }
        jumpClickType = true;
        if (BaseConstant.sharedPreferencesUtils.getBoolean(BaseConstant.SP.CACHE_IS_LOGIN)) {
            //跳转main模块主页
            RouteActivityUtils.toJumpMainModeIndexActivity(getContext(), new RouteActivityNavigationCallBack() {
                @Override
                public void onArrivalBack() {
                    finish();
                }

                @Override
                public void onLostBack() {
                    jumpClickType = false;
                }
            });
        } else {
            //跳转登录模块主页
            RouteActivityUtils.toJumpLoginModeIndexActivity(getContext(), new RouteActivityNavigationCallBack() {
                @Override
                public void onArrivalBack() {
                    finish();
                }

                @Override
                public void onLostBack() {
                    jumpClickType = false;
                }
            });
        }
    }

}
