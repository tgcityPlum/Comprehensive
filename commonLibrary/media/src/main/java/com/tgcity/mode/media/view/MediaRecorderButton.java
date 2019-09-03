package com.tgcity.mode.media.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tgcity.mode.media.manage.MediaRecorderManage;
import com.tgcity.mode.media.manage.MedioRecorderProgressManage;

/**
 * @author TGCity
 * @date 2019/9/2
 * @describe 控制视频录制的自定义按钮
 */
public class MediaRecorderButton extends Button implements MediaRecorderManage.VideoPreparedListener {

    private MediaRecorderManage mMediaRecorderManage;
    private MedioRecorderProgressManage progressManage;

    private boolean mRead = false;

    private boolean isRecording = false;

    /**
     * 开始录制
     */
    private static final int MSG_VIDEO_PREPARED = 0x110;
    /**
     * 正在录制
     */
    private static final int MSG_VIDEO_CHANGE = 0x111;
    /**
     * 结束录制
     */
    private static final int MSG_VIDEO_FINISH = 0x112;
    /**
     * 录制最长时间
     */
    private static float MAX_TIME = 7f;

    /**
     * 手指移动的距离
     */
    private static final int DISTANCE = 50;

    /**
     * 记录录制时间
     */
    private float mTime = 0;

    /**
     * 录制完成
     */
    private OnRecordingFinishListener mFinishListener;

    private static final int STATE_WANT_TO_CANCEL = 0;
    private static final int STATE_RECORDING = 1;
    private static final int STATE_NORMAL = 2;

    private int mCurrentState = STATE_NORMAL;

    private Toast mToast;

    public MediaRecorderButton(Context context) {
        this(context, null);
    }

    public MediaRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mMediaRecorderManage = MediaRecorderManage.getInstance();
        progressManage = new MedioRecorderProgressManage(context);

        mMediaRecorderManage.setVideoPreparedListener(this);
        //长按开始录制
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mRead = true;

                progressManage.showProgress((int) MAX_TIME);
                mMediaRecorderManage.prepareVideo();

                return false;
            }
        });
    }

    /**
     * 消息回调
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_VIDEO_PREPARED:
                    isRecording = true;
                    new Thread(timerRunnable).start();
                    break;
                case MSG_VIDEO_CHANGE:
                    progressManage.updateProgress((int) mTime);
                    break;
                case MSG_VIDEO_FINISH:
                    progressManage.dismissDialog();
                    reset();
                    break;
                default:
                    break;
            }
        }
    };

    private void reset() {
        mTime = 0;
        mRead = false;
        isRecording = false;
        stateChange(STATE_NORMAL);
    }

    private void stateChange(int state) {
        if (mCurrentState != state) {
            mCurrentState = state;
        }
        switch (state) {
            case STATE_NORMAL:
                setText("按住拍");
                break;
            case STATE_WANT_TO_CANCEL:
                progressManage.showAlertView();
                setText("松开取消录制");
                break;
            case STATE_RECORDING:
                setText("松开发送");
                progressManage.showSlideAlertView();
                break;
            default:
                break;
        }
    }

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            while (isRecording) {
                try {
                    Thread.sleep(100);
                    mTime += 0.1f;
                    handler.sendEmptyMessage(MSG_VIDEO_CHANGE);

                    if (mTime > MAX_TIME) {
                        if (mFinishListener != null && mCurrentState == STATE_RECORDING) {
                            //回调录制完成接口
                            handler.sendEmptyMessage(MSG_VIDEO_FINISH);
                            mMediaRecorderManage.release();
                            mFinishListener.onRecordingFinish(mTime, mMediaRecorderManage.getCurrentRecordFileName());
                        } else if (mCurrentState == STATE_WANT_TO_CANCEL) {
                            //达到最大录制时间时  是取消录制状态
                            handler.sendEmptyMessage(MSG_VIDEO_FINISH);
                            mMediaRecorderManage.cancel();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void wellPrepared() {
        handler.sendEmptyMessage(MSG_VIDEO_PREPARED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                stateChange(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_MOVE:
                if (isRecording) {
                    if (wantCancel(x, y)) {
                        stateChange(STATE_WANT_TO_CANCEL);
                    } else {
                        stateChange(STATE_RECORDING);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!mRead) {
                    reset();
                    return super.onTouchEvent(event);
                }
                if (!isRecording || mTime < 0.6f) {
                    toastShort();
                    mMediaRecorderManage.cancel();
                    progressManage.dismissDialog();
                } else if (mCurrentState == STATE_RECORDING) {
                    mMediaRecorderManage.release();
                    if (mFinishListener != null) {
                        progressManage.dismissDialog();
                        mFinishListener.onRecordingFinish(mTime, mMediaRecorderManage.getCurrentRecordFileName());
                    }
                } else if (mCurrentState == STATE_WANT_TO_CANCEL) {
                    mMediaRecorderManage.cancel();
                    progressManage.dismissDialog();
                }
                reset();
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    private void toastShort() {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), "录制时间太短！", Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    private boolean wantCancel(int x, int y) {
        if (y < -DISTANCE || y > getHeight() + DISTANCE) {
            return true;
        }
        return false;
    }

    public void setFinishListener(OnRecordingFinishListener mFinishListener) {
        this.mFinishListener = mFinishListener;
    }

    /**
     * 设置最长录制时间
     */
    public void setLongRecordTime(float recordTime) {
        MAX_TIME = recordTime + 1f;
    }

    /**
     * 录制完成回调接口
     */
    public interface OnRecordingFinishListener {
        void onRecordingFinish(float seconds, String fileName);
    }

}
