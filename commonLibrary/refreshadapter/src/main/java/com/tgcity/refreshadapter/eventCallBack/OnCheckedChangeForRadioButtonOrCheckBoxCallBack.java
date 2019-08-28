package com.tgcity.refreshadapter.eventCallBack;

import android.support.annotation.IdRes;
import android.widget.CompoundButton;

import com.tgcity.refreshadapter.BaseViewHolder;

/**
 * @author TGCity
 * 本回调是为了解决参数常量化的问题
 */

@SuppressWarnings("ALL")
public class OnCheckedChangeForRadioButtonOrCheckBoxCallBack<T, K extends BaseViewHolder> implements CompoundButton.OnCheckedChangeListener {
    private int viewId;
    private K helper;
    private T item;
    private OnCheckedChange onCheckedChange;

    public OnCheckedChangeForRadioButtonOrCheckBoxCallBack(int viewId, K helper, T item, OnCheckedChange onCheckedChange) {
        this.viewId = viewId;
        this.helper = helper;
        this.item = item;
        this.onCheckedChange = onCheckedChange;
    }

    public int getViewId() {
        return viewId;
    }

    public OnCheckedChangeForRadioButtonOrCheckBoxCallBack setViewId(int viewId) {
        this.viewId = viewId;
        return this;
    }

    public K getHelper() {
        return helper;
    }

    public OnCheckedChangeForRadioButtonOrCheckBoxCallBack setHelper(K helper) {
        this.helper = helper;
        return this;
    }

    public T getItem() {
        return item;
    }

    public OnCheckedChangeForRadioButtonOrCheckBoxCallBack setItem(T item) {
        this.item = item;
        return this;
    }

    public OnCheckedChangeForRadioButtonOrCheckBoxCallBack setOnCheckedChange(OnCheckedChange onCheckedChange) {
        this.onCheckedChange = onCheckedChange;
        return this;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (onCheckedChange != null) {
            onCheckedChange.onCheckedChange(viewId, helper, item, isChecked);
        }
    }

    public interface OnCheckedChange<T, K extends BaseViewHolder> {

        /**
         * onCheckedChange
         * @param viewId    int
         * @param helper    K
         * @param item      T
         * @param isChecked boolean
         */
        void onCheckedChange(@IdRes int viewId, K helper, T item, boolean isChecked);
    }
}
