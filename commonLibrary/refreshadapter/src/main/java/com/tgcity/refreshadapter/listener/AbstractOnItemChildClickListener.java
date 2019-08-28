package com.tgcity.refreshadapter.listener;

import android.view.View;

import com.tgcity.refreshadapter.BaseQuickAdapter;

/**
 * @author TGCity
 * A convenience class to extend when you only want to AbstractOnItemChildClickListener for a subset
 * of all the AbstractSimpleClickListener. This implements all methods in the
 * {@link AbstractSimpleClickListener}
 */

public abstract class AbstractOnItemChildClickListener extends AbstractSimpleClickListener {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemChildClick(adapter, view, position);
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    /**
     * onSimpleItemChildClick
     * @param adapter   BaseQuickAdapter
     * @param view      View
     * @param position  int
     */
    public abstract void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position);
}
