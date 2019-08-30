package com.tgcity.base.activity;

import android.support.v7.app.AppCompatActivity;

import com.tgcity.base.utils.LogUtils;
import com.tgcity.base.widget.dialog.BaseLoadingDialog;

/**
 * @author TGCity
 * 基础的activity
 * --处理加载指示器
 */
public class BaseLoadingActivity extends AppCompatActivity {
    /**
     * BaseLoadingDialog
     */
    private BaseLoadingDialog mBaseLoadingDialog;

    /**
     * show loading
     */
    public void showLoadingDialog() {
        if (mBaseLoadingDialog == null) {
            mBaseLoadingDialog = new BaseLoadingDialog(this);
        }

        if (!mBaseLoadingDialog.isShowing()) {
            mBaseLoadingDialog.show();
            LogUtils.i("BaseLoadingDialog is show");
        }
    }

    /**
     * dismiss loading
     */
    public void dismissLoadingDialog() {
        if (mBaseLoadingDialog != null) {
            mBaseLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //destroy loading
        dismissLoadingDialog();
        if (mBaseLoadingDialog != null) {
            mBaseLoadingDialog.clear();
        }
    }
}
