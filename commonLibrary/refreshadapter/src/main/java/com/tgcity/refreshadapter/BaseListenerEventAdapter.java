package com.tgcity.refreshadapter;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tgcity.base.utils.check.AntiShake;
import com.tgcity.refreshadapter.eventCallBack.OnCheckedChangeForRadioButtonOrCheckBoxCallBack;
import com.tgcity.refreshadapter.eventCallBack.OnCheckedChangeForRadioGroupCallBack;
import com.tgcity.refreshadapter.eventCallBack.OnClickCallBack;
import com.tgcity.refreshadapter.eventCallBack.OnTouchCallBack;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TGCity
 * 本层存在的目的是原库中未对监听事件做复用处理而导致了无端的内存消耗，故本层使用view的tag绑定使监听对象绑定到对应的view中，从而达到一个监听对象复用的目的
 */

@SuppressWarnings("ALL")
public abstract class BaseListenerEventAdapter<T, K extends BaseViewHolder> extends BaseMemoryAdapter<T, K> {

    /**
     * 被设置各种事件TAG的View集合
     */
    private List<WeakReference<View>> viewList = new ArrayList<>();

    public BaseListenerEventAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    /**
     * 清理垃圾
     */
    @Override
    public void clear() {
        super.clear();
        if (viewList != null) {
            for (int i = 0; i < viewList.size(); i++) {
                WeakReference<View> weakReference = viewList.get(i);
                if (weakReference != null) {
                    if (weakReference.get() != null) {
                        weakReference.get().setTag(weakReference.get().getId(), null);
                    }
                    weakReference.clear();
                }
                weakReference = null;
            }
            viewList.clear();
        }
        viewList = null;
    }

    /**
     * 设置点击事件
     */
    public void setOnClickListenerForView(@IdRes int viewId, K helper, T item) {
        if (helper.getTag(viewId) == null || !(helper.getTag(viewId) instanceof OnClickCallBack)) {
            OnClickCallBack onClickCallBack = new OnClickCallBack(viewId, helper, item, new OnClickCallBack.OnClick<T, K>() {

                @Override
                public void onClick(int viewId, K helper, T item) {
                    if (AntiShake.check400(viewId)) {
                        return;
                    }
                    BaseListenerEventAdapter.this.onClick(viewId, getCurrentPosition(helper), helper, item);
                }
            });
            helper.setTag(viewId, onClickCallBack);
            helper.getView(viewId).setOnClickListener(onClickCallBack);
        } else {
            ((OnClickCallBack) helper.getTag(viewId)).setViewId(viewId).setHelper(helper).setItem(item);
            helper.getView(viewId).setOnClickListener((OnClickCallBack) helper.getTag(viewId));
        }
        WeakReference<View> weakReference = new WeakReference<View>(helper.getView(viewId));
        viewList.add(weakReference);
    }

    /**
     * 设置触摸事件
     */
    public void setOnTouchListenerForView(@IdRes int viewId, K helper, T item) {
        if (helper.getView(viewId) == null || !(helper.getTag(viewId) instanceof OnTouchCallBack)) {
            OnTouchCallBack onTouchCallBack = new OnTouchCallBack(viewId, helper, item, new OnTouchCallBack.OnTouch<T, K>() {
                @Override
                public boolean onTouch(int viewId, K helper, T item, MotionEvent event) {
                    return BaseListenerEventAdapter.this.onTouch(viewId, getCurrentPosition(helper), helper, item, event);
                }
            });
            helper.setTag(viewId, onTouchCallBack);
            helper.getView(viewId).setOnTouchListener(onTouchCallBack);
        } else {
            ((OnTouchCallBack) helper.getTag(viewId)).setViewId(viewId).setHelper(helper).setItem(item);
            helper.getView(viewId).setOnTouchListener((OnTouchCallBack) helper.getTag(viewId));
        }
        WeakReference<View> weakReference = new WeakReference<View>(helper.getView(viewId));
        viewList.add(weakReference);
    }


    /**
     * 为RadioGroup设置选中改变事件
     */
    public void setOnCheckedChangeListenerForRadioGroup(@IdRes int viewId, K helper, T item) {
        if (helper.getView(viewId) == null || !(helper.getTag(viewId) instanceof OnCheckedChangeForRadioGroupCallBack)) {
            OnCheckedChangeForRadioGroupCallBack onCheckedChangeCallBack = new OnCheckedChangeForRadioGroupCallBack(viewId, helper, item, new OnCheckedChangeForRadioGroupCallBack.OnCheckedChange<T, K>() {
                @Override
                public void onCheckedChange(int viewId, K helper, T item, int checkedId) {
                    BaseListenerEventAdapter.this.onCheckedChangedForRadioGroup(viewId, getCurrentPosition(helper), helper, item, checkedId);
                }

            });
            helper.setTag(viewId, onCheckedChangeCallBack);
            ((RadioGroup) helper.getView(viewId)).setOnCheckedChangeListener(onCheckedChangeCallBack);
        } else {
            ((OnCheckedChangeForRadioGroupCallBack) helper.getTag(viewId)).setViewId(viewId).setHelper(helper).setItem(item);
            ((RadioGroup) helper.getView(viewId)).setOnCheckedChangeListener((OnCheckedChangeForRadioGroupCallBack) helper.getTag(viewId));
        }
        WeakReference<View> weakReference = new WeakReference<View>(helper.getView(viewId));
        viewList.add(weakReference);
    }


    /**
     * 为RadioButton或CheckBox设置选中改变事件
     */
    public void setOnCheckedChangeListenerForRadioButtonOrCheckBox(@IdRes int viewId, K helper, T item) {
        if (helper.getView(viewId) == null ||!(helper.getView(viewId) instanceof RadioButton) && !(helper.getView(viewId) instanceof CheckBox)) {
            return;
        }
        if (helper.getTag(viewId) == null ||!(helper.getTag(viewId) instanceof CompoundButton.OnCheckedChangeListener)) {
            OnCheckedChangeForRadioButtonOrCheckBoxCallBack onCheckedChangeForRadioButtonOrCheckBoxCallBack = new OnCheckedChangeForRadioButtonOrCheckBoxCallBack(viewId, helper, item, new OnCheckedChangeForRadioButtonOrCheckBoxCallBack.OnCheckedChange<T, K>() {
                @Override
                public void onCheckedChange(int viewId, K helper, T item, boolean isChecked) {
                    BaseListenerEventAdapter.this.onCheckedChangedForRadioButtonOrCheckBox(viewId, getCurrentPosition(helper), helper, item, isChecked);
                }
            });
            if (helper.getView(viewId) instanceof RadioButton) {
                ((RadioButton) helper.getView(viewId)).setOnCheckedChangeListener(onCheckedChangeForRadioButtonOrCheckBoxCallBack);
            }
            if (helper.getView(viewId) instanceof CheckBox) {
                ((CheckBox) helper.getView(viewId)).setOnCheckedChangeListener(onCheckedChangeForRadioButtonOrCheckBoxCallBack);
            }
            helper.setTag(viewId, onCheckedChangeForRadioButtonOrCheckBoxCallBack);
        } else {
            if (helper.getView(viewId) instanceof RadioButton) {
                ((RadioButton) helper.getView(viewId)).setOnCheckedChangeListener((OnCheckedChangeForRadioButtonOrCheckBoxCallBack) helper.getTag(viewId));
            }
            if (helper.getView(viewId) instanceof CheckBox) {
                ((CheckBox) helper.getView(viewId)).setOnCheckedChangeListener((OnCheckedChangeForRadioButtonOrCheckBoxCallBack) helper.getTag(viewId));
            }
        }
        WeakReference<View> weakReference = new WeakReference<View>(helper.getView(viewId));
        viewList.add(weakReference);
    }

    /**
     * 获取当前Item的Position(减去头部布局的数量)
     */
    public int getCurrentPosition(BaseViewHolder helper) {
        return helper.getAdapterPosition() - getHeaderLayoutCount();
    }


    protected void onClick(@IdRes int viewId, int position, K helper, T item) {

    }

    protected boolean onTouch(@IdRes int viewId, int position, K helper, T item, MotionEvent event) {
        return false;
    }

    protected void onCheckedChangedForRadioGroup(@IdRes int viewId, int position, K helper, T item, int checkedId) {

    }

    protected void onCheckedChangedForRadioButtonOrCheckBox(@IdRes int viewId, int position, K helper, T item, boolean isChecked) {

    }


}
