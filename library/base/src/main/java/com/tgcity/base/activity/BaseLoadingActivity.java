package com.tgcity.base.activity;

import android.support.v7.app.AppCompatActivity;

import com.tgcity.base.utils.LogUtils;
import com.tgcity.base.widget.dialog.BaseDialogLoading;

/**
 * 基础的activity
 * --处理加载指示器
 */
public class BaseLoadingActivity extends AppCompatActivity {
    //BaseDialogLoading
    private BaseDialogLoading mBaseDialogLoading;

    /**
     * show loading
     */
    public void showLoadingDialog() {
        if (mBaseDialogLoading == null) {
            mBaseDialogLoading = new BaseDialogLoading(this);
        }

        if (!mBaseDialogLoading.isShowing()) {
            mBaseDialogLoading.show();
            LogUtils.i("BaseDialogLoading is show");
        }
    }

    /**
     * dismiss loading
     */
    public void dismissLoadingDialog() {
        if (mBaseDialogLoading != null) {
            mBaseDialogLoading.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //destroy loading
        dismissLoadingDialog();
        if (mBaseDialogLoading != null) {
            mBaseDialogLoading.clear();
        }
    }
}
