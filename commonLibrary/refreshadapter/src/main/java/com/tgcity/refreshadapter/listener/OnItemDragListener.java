package com.tgcity.refreshadapter.listener;

import android.support.v7.widget.RecyclerView;

/**
 * @author TGCity
 */
public interface OnItemDragListener {

    /**
     * onItemDragStart
     * @param viewHolder    ViewHolder
     * @param pos   int
     */
    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos);

    /**
     * onItemDragMoving
     * @param source    ViewHolder
     * @param from      int
     * @param target    ViewHolder
     * @param to        int
     */
    void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to);

    /**
     * onItemDragEnd
     * @param viewHolder    ViewHolder
     * @param pos           int
     */
    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos);

}
