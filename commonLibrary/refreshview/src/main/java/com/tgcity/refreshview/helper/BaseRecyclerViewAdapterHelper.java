package com.tgcity.refreshview.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.tgcity.refreshadapter.BaseItemDraggableAdapter;
import com.tgcity.refreshadapter.BaseQuickAdapter;
import com.tgcity.refreshadapter.animation.BaseAnimation;
import com.tgcity.refreshadapter.callback.ItemDragAndSwipeCallback;
import com.tgcity.refreshadapter.listener.OnItemDragListener;
import com.tgcity.refreshadapter.listener.OnItemSwipeListener;
import com.tgcity.refreshadapter.loadmore.LoadMoreView;

/**
 * @author TGCity
 * 为了方便起见参考BaseRecyclerViewAdapter官方文档做了个常用功能的一键调用,方便统一管理
 * https://www.jianshu.com/p/b343fcff51b0
 */

public class BaseRecyclerViewAdapterHelper {

    /**
     * 单例
     */
    private static BaseRecyclerViewAdapterHelper mInstance;

    public static BaseRecyclerViewAdapterHelper getInstance() {
        if (mInstance == null) {
            mInstance = new BaseRecyclerViewAdapterHelper();
        }
        return mInstance;
    }

    /**
     * 设置自定义加载布局
     *
     * @param baseQuickAdapter 要关联的适配器
     * @param loadMoreView     加载布局
     */
    public BaseRecyclerViewAdapterHelper setLoadMoreView(BaseQuickAdapter baseQuickAdapter, LoadMoreView loadMoreView) {
        baseQuickAdapter.setLoadMoreView(loadMoreView);
        return this;
    }

    /**
     * 设置加载状态
     *
     * @param baseQuickAdapter 要关联的适配器
     * @param adapterStatus    加载状态
     */
    public BaseRecyclerViewAdapterHelper loadStatus(BaseQuickAdapter baseQuickAdapter, RefreshViewConstant.AdapterStatus adapterStatus) {
        return loadStatus(baseQuickAdapter,adapterStatus,false);
    }
    public BaseRecyclerViewAdapterHelper loadStatus(BaseQuickAdapter baseQuickAdapter, RefreshViewConstant.AdapterStatus adapterStatus, boolean gone) {
        if (RefreshViewConstant.AdapterStatus.complete == adapterStatus) {
            baseQuickAdapter.loadMoreComplete();
        } else if (RefreshViewConstant.AdapterStatus.fail == adapterStatus) {
            baseQuickAdapter.loadMoreFail();
        } else if (RefreshViewConstant.AdapterStatus.noMore == adapterStatus) {
            baseQuickAdapter.loadMoreEnd(gone);
        }
        return this;
    }

    /**
     * 打开或关闭加载（一般用于下拉的时候做处理，因为上拉下拉不能同时操作）
     *
     * @param baseQuickAdapter 要关联的适配器
     * @param enable           是否启用
     */
    public BaseRecyclerViewAdapterHelper setEnableLoadMore(BaseQuickAdapter baseQuickAdapter, boolean enable) {
        baseQuickAdapter.setEnableLoadMore(enable);
        return this;
    }

    /**
     * 预加载
     * 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
     *
     * @param baseQuickAdapter 要关联的适配器
     * @param number           从第N个开始预加载
     */
    public BaseRecyclerViewAdapterHelper setPreLoadNumber(BaseQuickAdapter baseQuickAdapter, int number) {
        baseQuickAdapter.setPreLoadNumber(number);
        return this;
    }

    /**
     * 默认第一次加载会进入回调，如果不需要可以配置
     *
     * @param baseQuickAdapter 要关联的适配器
     */
    public BaseRecyclerViewAdapterHelper disableLoadMoreIfNotFullPage(BaseQuickAdapter baseQuickAdapter) {
        baseQuickAdapter.disableLoadMoreIfNotFullPage();
        return this;
    }


    /**
     * 滑动最后一个Item的时候回调onLoadMoreRequested方法
     *
     * @param baseQuickAdapter        要关联的适配器
     * @param recyclerView            要关联的recyclerView
     * @param requestLoadMoreListener 加载监听
     */
    public BaseRecyclerViewAdapterHelper setOnLoadMoreListener(BaseQuickAdapter baseQuickAdapter, RecyclerView recyclerView, BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener) {
        baseQuickAdapter.setOnLoadMoreListener(requestLoadMoreListener, recyclerView);
        return this;
    }

    /**
     * 开启拖拽
     *
     * @param baseItemDraggableAdapter 要关联的适配器
     * @param toggleViewId             触发拖拽事件的item中的view
     * @param onItemDragListener       拖拽监听
     */
    public BaseRecyclerViewAdapterHelper dragItem(BaseItemDraggableAdapter baseItemDraggableAdapter, int toggleViewId, RecyclerView recyclerView, OnItemDragListener onItemDragListener) {
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(baseItemDraggableAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        itemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START);
        // 开启拖拽
        baseItemDraggableAdapter.enableDragItem(itemTouchHelper, toggleViewId, true);
        //设置拖拽监听
        baseItemDraggableAdapter.setOnItemDragListener(onItemDragListener);

        return this;
    }


    /**
     * 开启滑动删除
     *
     * @param baseItemDraggableAdapter 要关联的适配器
     * @param onItemSwipeListener      滑动删除监听
     */
    public BaseRecyclerViewAdapterHelper swipeItem(BaseItemDraggableAdapter baseItemDraggableAdapter, OnItemSwipeListener onItemSwipeListener) {
        // 开启滑动删除
        baseItemDraggableAdapter.enableSwipeItem();
        //设置滑动删除监听
        baseItemDraggableAdapter.setOnItemSwipeListener(onItemSwipeListener);
        return this;
    }

    /**
     * 添加头部
     *
     * @param quickAdapter 要关联的适配器
     * @param header       要添加的头部
     */
    public BaseRecyclerViewAdapterHelper addHeader(BaseQuickAdapter quickAdapter, View header) {
        //添加头部
        quickAdapter.addHeaderView(header);
        //同时支持头部与空布局
        quickAdapter.setHeaderAndEmpty(true);
        return this;
    }

    /**
     * 添加脚部
     *
     * @param quickAdapter 要关联的适配器
     * @param footer       要添加的脚部
     */
    public BaseRecyclerViewAdapterHelper addFooter(BaseQuickAdapter quickAdapter, View footer) {
        //添加脚部
        quickAdapter.addFooterView(footer);
        //同时支持脚部与空布局
        quickAdapter.setHeaderAndEmpty(true);
        return this;
    }

    /**
     * 删除所有头部
     *
     * @param quickAdapter 要关联的适配器
     */
    public BaseRecyclerViewAdapterHelper removeAllHeader(BaseQuickAdapter quickAdapter) {
        quickAdapter.removeAllHeaderView();
        return this;
    }

    /**
     * 删除所有脚部
     *
     * @param quickAdapter 要关联的适配器
     */
    public BaseRecyclerViewAdapterHelper removeAllFooter(BaseQuickAdapter quickAdapter) {
        quickAdapter.removeAllFooterView();
        return this;
    }

    /**
     * 开启动画
     *
     * @param quickAdapter  要关联的适配器
     * @param isFirstOnly   动画是否只显示一次
     * @param animationType 动画类型，比如BaseQuickAdapter.ALPHAIN
     */
    public BaseRecyclerViewAdapterHelper animation(BaseQuickAdapter quickAdapter, boolean isFirstOnly, int animationType) {
        //设置动画
        quickAdapter.openLoadAnimation(animationType);
        //动画是否只显示一次
        quickAdapter.isFirstOnly(isFirstOnly);
        return this;
    }

    /**
     * 自定义动画
     *
     * @param quickAdapter  要关联的适配器
     * @param baseAnimation 自定义动画
     */
    public BaseRecyclerViewAdapterHelper animationCustom(BaseQuickAdapter quickAdapter, BaseAnimation baseAnimation) {
        quickAdapter.openLoadAnimation(baseAnimation);
        return this;
    }


}
