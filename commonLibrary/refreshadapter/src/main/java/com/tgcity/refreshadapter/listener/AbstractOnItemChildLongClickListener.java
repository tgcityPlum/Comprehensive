package com.tgcity.refreshadapter.listener;

import android.view.View;

import com.tgcity.refreshadapter.BaseQuickAdapter;

/**
 * @author TGCity
 * A convenience class to extend when you only want to AbstractOnItemChildLongClickListener for a subset
 * of all the AbstractSimpleClickListener. This implements all methods in the
 * {@link AbstractSimpleClickListener}
 */
@SuppressWarnings("ALL")
public abstract class AbstractOnItemChildLongClickListener extends AbstractSimpleClickListener {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemChildLongClick(adapter, view, position);
    }

    /**
     * onSimpleItemChildLongClick
     * @param adapter   BaseQuickAdapter
     * @param view      View
     * @param position  int
     */
    public abstract void onSimpleItemChildLongClick(BaseQuickAdapter adapter, View view, int position);

}
