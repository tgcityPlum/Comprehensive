package com.tgcity.mode.media.manage;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tgcity.mode.media.R;

/**
 * @author TGCity
 * @date 2019/9/3
 * @describe 录制视频的进度条
 */
public class MedioRecorderProgressManage {

    private Context context;
    private Dialog dialog;

    /**
     * 提示用户松手取消
     */
    private TextView tvAlert;

    /**
     * 提示用户上滑取消
     */
    private TextView tvSlideAlert;

    private ProgressBar progressBar;

    public MedioRecorderProgressManage(Context context) {
        this.context = context;
    }

    /**
     * 显示Progress
     */
    public void showProgress(int max) {
        dialog = new Dialog(context, R.style.Theme_AudioDialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.diallog_layout, null);

        dialog.setContentView(view);

        tvAlert = view.findViewById(R.id.alertText);
        tvSlideAlert = view.findViewById(R.id.slideAlert);
        progressBar = view.findViewById(R.id.mProgressBar);

        progressBar.setMax(max);
        dialog.show();
    }

    /**
     * 松开取消
     */
    public void showAlertView() {
        if (dialog != null && dialog.isShowing()) {
            tvAlert.setVisibility(View.VISIBLE);
            tvSlideAlert.setVisibility(View.GONE);
        }
    }

    /**
     * 显示上滑取消
     */
    public void showSlideAlertView() {
        if (dialog != null && dialog.isShowing()) {
            tvAlert.setVisibility(View.GONE);
            tvSlideAlert.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 更新进度条状态
     */
    public void updateProgress(int timeCount) {
        if (dialog != null && dialog.isShowing()) {
            progressBar.setProgress(timeCount);
        }
    }

    /**
     * 关闭dialog
     */
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
