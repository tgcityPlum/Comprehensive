package com.tgcity.refreshadapter.listener;

import android.view.View;

import com.tgcity.refreshadapter.BaseQuickAdapter;

/**
 * @author TGCity
 */

public abstract class AbstractOnItemLongClickListener extends AbstractSimpleClickListener {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemLongClick(adapter, view, position);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    /**
     * onSimpleItemLongClick
     * @param adapter   BaseQuickAdapter
     * @param view      View
     * @param position  int
     */
    public abstract void onSimpleItemLongClick(BaseQuickAdapter adapter, View view, int position);
}
