package com.tgcity.refreshadapter.provider;

import android.content.Context;

import com.tgcity.refreshadapter.BaseViewHolder;

import java.util.List;

/**
 * @author TGCity
 * https://github.com/chaychan
 * @description: The base class of ItemProvider
 */

public abstract class BaseItemProvider<T,V extends BaseViewHolder> {

    public Context mContext;
    public List<T> mData;

    /**
     * 子类须重写该方法返回viewType
     * @return Rewrite this method to return viewType
     */
    public abstract int viewType();

    /**
     * 子类须重写该方法返回layout
     * @return  Rewrite this method to return layout
     */
    public abstract int layout();

    public abstract void convert(V helper, T data, int position);

    //
    //Subclasses override this method if you want to implement an item click event

    /**
     * 子类若想实现条目点击事件则重写该方法
     * @param helper    V
     * @param data      T
     * @param position  int
     */
    public void onClick(V helper, T data, int position){}

    /**
     * 子类若想实现条目长按事件则重写该方法
     * @param helper    V
     * @param data      T
     * @param position  int
     * @return  Subclasses override this method if you want to implement an item long press event
     */
    public boolean onLongClick(V helper, T data, int position){return false;}
}
