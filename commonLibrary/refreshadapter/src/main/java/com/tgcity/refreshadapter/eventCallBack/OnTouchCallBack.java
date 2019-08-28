package com.tgcity.refreshadapter.eventCallBack;

import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.view.View;

import com.tgcity.refreshadapter.BaseViewHolder;

/**
 * @author TGCity
 * 本回调是为了解决参数常量化的问题
 */

@SuppressWarnings("ALL")
public class OnTouchCallBack<T, K extends BaseViewHolder> implements View.OnTouchListener {
    private int viewId;
    private K helper;
    private T item;
    private OnTouch onTouch;

    public OnTouchCallBack(int viewId, K helper, T item, OnTouch onTouch) {
        this.viewId = viewId;
        this.helper = helper;
        this.item = item;
        this.onTouch = onTouch;
    }

    public int getViewId() {
        return viewId;
    }

    public OnTouchCallBack setViewId(int viewId) {
        this.viewId = viewId;
        return this;
    }

    public K getHelper() {
        return helper;
    }

    public OnTouchCallBack setHelper(K helper) {
        this.helper = helper;
        return this;
    }

    public T getItem() {
        return item;
    }

    public OnTouchCallBack setItem(T item) {
        this.item = item;
        return this;
    }

    public OnTouchCallBack setOnTouch(OnTouch onTouch) {
        this.onTouch = onTouch;
        return this;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return onTouch == null ? false : onTouch.onTouch(viewId, helper, item,event);
    }

    public interface OnTouch<T, K extends BaseViewHolder> {
        boolean onTouch(@IdRes int viewId, K helper, T item, MotionEvent event);
    }
}
