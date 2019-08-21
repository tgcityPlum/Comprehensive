package com.tgcity.refreshadapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.tgcity.base.application.BaseApplication;
import com.tgcity.base.utils.imageloader.ImageHelper;

import java.util.List;

/**
 * 本层专门处理适配器内存占用
 * Created by Administrator on 2019/2/21.
 */

public abstract class BaseMemoryAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    ImageHelper.resume(BaseApplication.getInstances().getApplicationContext());
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    ImageHelper.pause(BaseApplication.getInstances().getApplicationContext());
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    ImageHelper.pause(BaseApplication.getInstances().getApplicationContext());
                    break;

            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    public BaseMemoryAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        setOnBindRecyclerViewCallBack(new OnBindRecyclerViewCallBack() {
            @Override
            public void onBindRecyclerView(RecyclerView recyclerView) {
                if (recyclerView != null) {
                    recyclerView.addOnScrollListener(onScrollListener);
                }
            }
        });
    }


    /**
     * 清理垃圾
     */
    public void clear(){
        super.clear();
        if (getRecyclerView() != null) {
            getRecyclerView().removeOnScrollListener(onScrollListener);
        }
        onScrollListener = null;
    }




}
