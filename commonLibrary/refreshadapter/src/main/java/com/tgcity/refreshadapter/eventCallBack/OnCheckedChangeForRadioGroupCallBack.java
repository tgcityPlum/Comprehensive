package com.tgcity.refreshadapter.eventCallBack;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import com.tgcity.refreshadapter.BaseViewHolder;

/**
 * @author TGCity
 * 本回调是为了解决参数常量化的问题
 */

@SuppressWarnings("ALL")
public class OnCheckedChangeForRadioGroupCallBack<T, K extends BaseViewHolder> implements RadioGroup.OnCheckedChangeListener {
    private int viewId;
    private K helper;
    private T item;
    private OnCheckedChange onCheckedChange;

    public OnCheckedChangeForRadioGroupCallBack(int viewId, K helper, T item, OnCheckedChange onCheckedChange) {
        this.viewId = viewId;
        this.helper = helper;
        this.item = item;
        this.onCheckedChange = onCheckedChange;
    }

    public int getViewId() {
        return viewId;
    }

    public OnCheckedChangeForRadioGroupCallBack setViewId(int viewId) {
        this.viewId = viewId;
        return this;
    }

    public K getHelper() {
        return helper;
    }

    public OnCheckedChangeForRadioGroupCallBack setHelper(K helper) {
        this.helper = helper;
        return this;
    }

    public T getItem() {
        return item;
    }

    public OnCheckedChangeForRadioGroupCallBack setItem(T item) {
        this.item = item;
        return this;
    }

    public OnCheckedChangeForRadioGroupCallBack setOnCheckedChange(OnCheckedChange onCheckedChange) {
        this.onCheckedChange = onCheckedChange;
        return this;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (onCheckedChange != null) {
            onCheckedChange.onCheckedChange(viewId, helper, item, checkedId);
        }
    }

    public interface OnCheckedChange<T, K extends BaseViewHolder> {

        /**
         * onCheckedChange
         * @param viewId    int
         * @param helper    K
         * @param item      T
         * @param checkedId int
         */
        void onCheckedChange(@IdRes int viewId, K helper, T item, int checkedId);
    }
}
