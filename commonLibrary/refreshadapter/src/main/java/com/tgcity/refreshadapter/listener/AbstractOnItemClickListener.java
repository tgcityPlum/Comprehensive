package com.tgcity.refreshadapter.listener;

import android.view.View;

import com.tgcity.refreshadapter.BaseQuickAdapter;


/**
 * @author TGCity
 * A convenience class to extend when you only want to AbstractOnItemClickListener for a subset
 * of all the AbstractSimpleClickListener. This implements all methods in the
 * {@link AbstractSimpleClickListener}
 */
public abstract class AbstractOnItemClickListener extends AbstractSimpleClickListener {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemClick(adapter, view, position);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    /**
     * onSimpleItemClick
     * @param adapter   BaseQuickAdapter
     * @param view      View
     * @param position  int
     */
    public abstract void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position);
}
