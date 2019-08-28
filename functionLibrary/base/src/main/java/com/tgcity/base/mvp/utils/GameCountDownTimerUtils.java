package com.tgcity.base.mvp.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import java.util.Random;

/**
 * @author TGCity
 * 游戏定时器,Copy了系统的CountDownTimer在此基础上修改而来
 * 增加了模拟随机间隔时间，暂停继续，解决了内存泄漏的问题
 */
@SuppressWarnings("ALL")
public class GameCountDownTimerUtils {
    /**
     * 消息发送间隔时间，取安卓平台发生卡顿的底线，所以本定时器的最大精度为正负16
     */
    private long delay = 16;
    /**
     * 消息标识符
     */
    private int MSG = 1;
    /**
     * 启动到停止的毫秒数，也就是执行完成的总时间间隔
     */
    private long mMillisInFuture;
    /**
     * 计时间隔
     */
    private long mCountdownInterval;
    /**
     * 随机数生成器
     */
    private Random random;
    /**
     * 随机范围
     */
    private int randomLowTime;
    /**
     * 随机范围
     */
    private int randomHeightTime;
    /**
     * 是否使用随机模式
     */
    private boolean isRandomIntervalTime = false;
    /**
     * 未来某一时刻停止的毫秒数
     */
    private long mStopTimeInFuture;
    /**
     * 是否已取消
     */
    private boolean mCancelled = false;
    /**
     * 预期最小的生成的随机执行时间
     */
    private int randomTimeOffset = 1000;
    /**
     * 如果生成的时间比预定的时间小，是否重新递归生成新的间隔时间
     */
    private boolean createTime = false;
    /**
     * 是否已经结束
     */
    private boolean isFinished = true;

    public GameCountDownTimerUtils(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
        random = new Random();
    }

    /**
     * 完成的总时间间隔
     *
     * @param mMillisInFuture
     */
    public GameCountDownTimerUtils setmMillisInFuture(long mMillisInFuture) {
        this.mMillisInFuture = mMillisInFuture;
        return this;
    }

    /**
     * 设置计时间隔(单位：秒)
     *
     * @param mCountdownInterval
     * @return
     */
    public GameCountDownTimerUtils setmCountdownInterval(int mCountdownInterval) {
        this.mCountdownInterval = mCountdownInterval;
        return this;
    }


    /**
     * 设置随机数最高取值范围(单位：秒)
     *
     * @param randomHeightTime
     */
    public GameCountDownTimerUtils setRandomHeightTime(int randomHeightTime) {
        this.randomHeightTime = randomHeightTime;
        return this;
    }

    /**
     * 计时是否已经结束
     *
     * @return
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * 如果生成的时间比预定的时间小，是否重新递归生成新的间隔时间
     *
     * @param createTime
     */
    public GameCountDownTimerUtils setCreateTime(boolean createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 设置随机数最低取值范围
     *
     * @param randomLowTime
     */
    public GameCountDownTimerUtils setRandomLowTime(int randomLowTime) {
        this.randomLowTime = randomLowTime;
        return this;
    }

    /**
     * 是否启用随机间隔时间模式
     *
     * @param randomIntervalTime
     * @return
     */
    public GameCountDownTimerUtils setRandomIntervalTime(boolean randomIntervalTime) {
        isRandomIntervalTime = randomIntervalTime;
        return this;
    }

    /**
     * 取消计时
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    /**
     * 继续计时
     */
    public void continueTime() {
        mCancelled = false;
        MSG++;//防止原来的标识符干扰，所以将标识符+1以与原来的做区分
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
    }

    /**
     * 开始计时
     *
     * @return
     */
    public synchronized final GameCountDownTimerUtils start() {
        if (!isFinished) {
            return this;
        }
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            isFinished = true;
            return this;
        }
        isFinished = false;
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }


    /**
     * 清理垃圾
     */
    public void clear() {
        cancel();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        mHandler = null;
        random = null;
    }

    /**
     * 获取剩余时间
     *
     * @return
     */
    public long getStopTimeInFuture() {
        return mStopTimeInFuture - SystemClock.elapsedRealtime();
    }

    /**
     * 创建随机执行时间
     *
     * @return
     */
    private int createRandomCountdownInterval() {
        int temp = random.nextInt(randomHeightTime - randomLowTime) + randomLowTime;
        //如果生成的时间比预定的时间小，则重新递归生成新的间隔时间
        if (createTime && (temp - mCountdownInterval < randomTimeOffset)) {
            createRandomCountdownInterval();
        }
        return temp;
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            synchronized (GameCountDownTimerUtils.this) {
                if (!mCancelled && MSG == msg.what) {
                    long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
                    if (millisLeft <= 0) {
                        onFinish();
                        isFinished = true;
                    } else {
                        isFinished = false;
                        long lastTickStart = SystemClock.elapsedRealtime();
//                        onTick(millisLeft);//这一行是系统原代码，但是经测试，线程经过CPU处理时会有一个误差，所以用取整+取余的办法把精确度提高到差不多0.001ms
                        int offset = ((millisLeft % mCountdownInterval) > 0 ? 1 : 0);
//                            LogUtils.e(millisLeft / mCountdownInterval + "---" + offset);
                        onTick(millisLeft, (int) (millisLeft / mCountdownInterval + offset));
                        long lastTickDuration = SystemClock.elapsedRealtime() - lastTickStart;

                        if (millisLeft < mCountdownInterval) {
                            delay = millisLeft - lastTickDuration;
                            if (delay < 0) {
                                delay = 0;
                            }
                        } else {
                            delay = mCountdownInterval - lastTickDuration;
                            while (delay < 0) {
                                delay += mCountdownInterval;
                            }
                        }
                    }
                    //继续发送消息，不至于handler死掉,但是会带来内存泄漏的风险，这里加上判断当剩余时间达到0时不再发送消息
                    if (getStopTimeInFuture() >= 0) {
                        sendMessageDelayed(obtainMessage(MSG), delay);
                        //生成下一次的正常操作间隔
                        if (isRandomIntervalTime) {
                            mCountdownInterval = createRandomCountdownInterval();
                        }
                    }
                }
            }
        }
    };

    public void onTick(long millisUntilFinished, int second){

    };

    public void onFinish(){

    };
}