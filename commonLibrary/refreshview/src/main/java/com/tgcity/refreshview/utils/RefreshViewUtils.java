package com.tgcity.refreshview.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.tgcity.base.mvp.model.OnDepositRequestCompleteCallBack;
import com.tgcity.base.mvp.model.OnDepositRequestPrepareListCallBack;
import com.tgcity.base.mvp.model.OnPublicRefreshViewCallBack;
import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.base.widget.progress.ProgressView;
import com.tgcity.refreshadapter.BaseQuickAdapter;
import com.tgcity.refreshadapter.loadmore.SimpleLoadMoreView;
import com.tgcity.refreshview.helper.BaseRecyclerViewAdapterHelper;
import com.tgcity.refreshview.helper.RefreshViewConstant;
import com.tgcity.refreshview.springview.container.DefaultHeader;
import com.tgcity.refreshview.springview.widget.SpringView;

import java.util.List;

/**
 * @author TGCity
 * 刷新工具类
 */
public class RefreshViewUtils {

    /**
     * 全局通用的处理请求成功后的列表逻辑（自动/不自动加载）
     *
     * @param isNoMoreNeedAutoLoad             是否不再需要自动加载(如果为true,则表示一次性加载全部数据且不再自动加载，各种回调也不再响应；如果为false,则是正常的列表加载逻辑)
     * @param refresh                          上下拉刷新控件
     * @param process                          状态页
     * @param adapter                          适配器
     * @param pageIndex                        下标(如果小于0，则效果跟isNoMoreNeedAutoLoad设为true一致)
     * @param list                             数据集合
     * @param onPublicRefreshViewCallBack      通用的列表加载动作回调
     * @param onDepositRequestCompleteCallBack 下标处理回调
     * @param <T>
     */
    public static <T> void depositRequestComplete(boolean isNoMoreNeedAutoLoad, SpringView refresh, ProgressView process, BaseQuickAdapter adapter, int pageIndex, List<T> list, OnPublicRefreshViewCallBack onPublicRefreshViewCallBack, OnDepositRequestCompleteCallBack onDepositRequestCompleteCallBack) {
        depositRequestComplete(isNoMoreNeedAutoLoad, true, refresh, process, adapter, pageIndex, list, onPublicRefreshViewCallBack, onDepositRequestCompleteCallBack);
    }

    /**
     * 全局通用的处理请求成功后的列表逻辑（完整的全套逻辑）
     *
     * @param isNoMoreNeedAutoLoad             是否不再需要自动加载(如果为true,则表示一次性加载全部数据且不再自动加载，各种回调也不再响应；如果为false,则是正常的列表加载逻辑)
     * @param disableLoadMoreIfNotFullPage     默认第一次加载会进入回调，如果不需要可以配置
     * @param refresh                          上下拉刷新控件
     * @param process                          状态页
     * @param adapter                          适配器
     * @param pageIndex                        下标(如果小于0，则效果跟isNoMoreNeedAutoLoad设为true一致)
     * @param list                             数据集合
     * @param onPublicRefreshViewCallBack      通用的列表加载动作回调
     * @param onDepositRequestCompleteCallBack 下标处理回调
     * @param <T>
     */
    public static <T> void depositRequestComplete(boolean isNoMoreNeedAutoLoad, boolean disableLoadMoreIfNotFullPage, SpringView refresh, ProgressView process, BaseQuickAdapter adapter, int pageIndex, List<T> list, OnPublicRefreshViewCallBack onPublicRefreshViewCallBack, OnDepositRequestCompleteCallBack onDepositRequestCompleteCallBack) {
        if (process != null) {
            process.showContent();
        }
        if (adapter == null) {
            return;
        }
        if (list == null) {
            if (onPublicRefreshViewCallBack != null) {
                onPublicRefreshViewCallBack.showEmpty();
            }
        }
        if (isNoMoreNeedAutoLoad) {
            adapter.setNewData(list);
            if (refresh != null) {
                refresh.setEnable(false);
            }
            BaseRecyclerViewAdapterHelper.getInstance().loadStatus(adapter, RefreshViewConstant.AdapterStatus.noMore);
        } else {
            if (refresh != null) {
                refresh.onFinishFreshAndLoad();
            }

            if (pageIndex < 0) {
                adapter.setNewData(list);
                if (refresh != null) {
                    refresh.setEnable(false);
                }
                BaseRecyclerViewAdapterHelper.getInstance().loadStatus(adapter, RefreshViewConstant.AdapterStatus.noMore);
                return;
            }

            if (pageIndex == 1 && (list == null || list.size() == 0)) {
                if (onPublicRefreshViewCallBack != null) {
                    onPublicRefreshViewCallBack.showEmpty();
                }
                BaseRecyclerViewAdapterHelper.getInstance().loadStatus(adapter, RefreshViewConstant.AdapterStatus.noMore);
                return;
            }

            if (list == null || list.size() == 0) {
                BaseRecyclerViewAdapterHelper.getInstance().loadStatus(adapter, RefreshViewConstant.AdapterStatus.noMore);
                return;
            }

            if (pageIndex == 1) {
                adapter.setNewData(list);
            } else {
                adapter.addData(list);
            }
            BaseRecyclerViewAdapterHelper.getInstance().loadStatus(adapter, RefreshViewConstant.AdapterStatus.complete);
            if (disableLoadMoreIfNotFullPage) {
                BaseRecyclerViewAdapterHelper.getInstance().disableLoadMoreIfNotFullPage(adapter);
            }
            if (onDepositRequestCompleteCallBack != null) {
                int a = pageIndex + 1;
                onDepositRequestCompleteCallBack.onPageIndexChanged(a);
            }
        }
    }

    /**
     * 全局通用的绑定RecyclerView、SpringView和适配器
     *
     * @param context
     * @param layoutManager                       布局管理器
     * @param recyclerView                        列表
     * @param refresh                             上下拉刷新控件
     * @param adapter                             适配器
     * @param onDepositRequestPrepareListCallBack 上下拉回调
     * @return
     */
    public static DefaultHeader bindRecyclerViewWithRefreshAndAdapter(Context context, RecyclerView.LayoutManager layoutManager, RecyclerView recyclerView, SpringView refresh, BaseQuickAdapter adapter, final OnDepositRequestPrepareListCallBack onDepositRequestPrepareListCallBack) {
        if (recyclerView == null) {
            return null;
        }
        DefaultHeader defaultHeader = null;
//        recyclerView.setItemAnimator(null);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        BaseRecyclerViewAdapterHelper.getInstance().setLoadMoreView(adapter, new SimpleLoadMoreView());
        BaseRecyclerViewAdapterHelper.getInstance().setOnLoadMoreListener(adapter, recyclerView, new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (onDepositRequestPrepareListCallBack != null) {
                    onDepositRequestPrepareListCallBack.onRequestLoadMore();
                }
            }
        });
        if (refresh != null) {
            defaultHeader = new DefaultHeader(context);
            defaultHeader.setOnExceptionEventClickListener(new SpringView.OnExceptionEventClickListener() {
                @Override
                public void onExceptionClick(ErrorMode errorMode, Context context) {
                    if (onDepositRequestPrepareListCallBack != null) {
                        onDepositRequestPrepareListCallBack.onExceptionClick(errorMode);
                    }
                }
            });
            refresh.setHeader(defaultHeader);
            refresh.setListener(new SpringView.OnFreshListener() {
                @Override
                public void onPreDrag(boolean HeaderOrFooter) {

                }

                @Override
                public void onRefresh() {
                    if (onDepositRequestPrepareListCallBack != null) {
                        onDepositRequestPrepareListCallBack.onRequestRefresh();
                    }
                }

                @Override
                public void onLoadmore() {

                }
            });
        }
        return defaultHeader;
    }

}
