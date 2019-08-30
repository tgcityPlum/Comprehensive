package com.tgcity.network.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.tgcity.network.R;
import com.tgcity.network.callback.AbstractSimpleCallBack;
import com.tgcity.base.widget.dialog.NetworkLoadingDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @author TGCity
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressSubscriber<T> extends AbstractSimpleCallBack<T> {

    private SubscriberOnNextListener mSubscriberOnNextListener;


    private Context context;
    private NetworkLoadingDialog networkLoadingDialog;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        networkLoadingDialog = new NetworkLoadingDialog(context, R.style.MyDialog1);
        networkLoadingDialog.setCanceledOnTouchOutside(false);
    }

    private void showProgressDialog(){
        try {
            if (networkLoadingDialog != null) {
                networkLoadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissProgressDialog(){
        try {
            if (networkLoadingDialog != null) {
                networkLoadingDialog.dismiss();
                networkLoadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        try {
            showProgressDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onCompleted();
        }
        try {
            dismissProgressDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e);
        }
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
}