package com.tgcity.refreshadapter.eventCallBack;

import android.support.annotation.IdRes;
import android.view.View;

import com.tgcity.refreshadapter.BaseViewHolder;


/**
 * 本回调是为了解决参数常量化的问题
 * Created by Administrator on 2019/3/4.
 */

@SuppressWarnings("ALL")
public class OnClickCallBack<T, K extends BaseViewHolder> implements View.OnClickListener {
    private int viewId;
    private K helper;
    private T item;
    private OnClick onClick;



    public OnClickCallBack(int viewId, K helper, T item,OnClick onClick) {
        this.viewId = viewId;
        this.helper = helper;
        this.item = item;
        this.onClick=onClick;
    }

    public int getViewId() {
        return viewId;
    }

    public OnClickCallBack setViewId(int viewId) {
        this.viewId = viewId;
        return this;
    }

    public K getHelper() {
        return helper;
    }

    public OnClickCallBack setHelper(K helper) {
        this.helper = helper;
        return this;
    }

    public T getItem() {
        return item;
    }

    public OnClickCallBack setItem(T item) {
        this.item = item;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (onClick != null) {
            onClick.onClick(viewId,  helper, item);
        }
    }

    public interface OnClick<T, K extends BaseViewHolder>{
         void onClick(@IdRes int viewId, K helper, T item);
    }
}
