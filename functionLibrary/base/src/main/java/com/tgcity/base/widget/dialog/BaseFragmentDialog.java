package com.tgcity.base.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragmentV4;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.tgcity.base.utils.ClearViewUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Describe 对话框基类
 */
public abstract class BaseFragmentDialog extends DialogFragmentV4 {
    private View rootView;

    private static final String TAG = BaseFragmentDialog.class.getSimpleName();

    //默认遮罩透明度
    private static final float DEFAULT_MASK_TRANS = 0.8f;

    private int mGravity = Gravity.BOTTOM;

    Unbinder unbinder;

    private OnBaseDialogFragmentCallBack onBaseDialogFragmentCallBack;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragmentV4.STYLE_NO_TITLE, getStyleRes());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        rootView = inflater.inflate(getLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        bindView(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ClearViewUtils.clearAll(rootView);
    }

    /**
     * 获取布局文件
     *
     * @return  int
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * 绑定的view处理
     *
     * @param v View
     */
    public abstract void bindView(View v);

    /**
     * 获取主题
     *
     * @return  int
     */
    public abstract int getStyleRes();

    @Override
    public void onStart() {
        super.onStart();
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = getDimAmount();
        params.width = getWidth() > 0 ? getWidth() : WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = getHeight() > 0 ? getHeight() : WindowManager.LayoutParams.WRAP_CONTENT;
        if (isGravity()) {
            params.gravity = getGravity() == 0 ? mGravity : getGravity();
        }
        window.setAttributes(params);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());
        getDialog().setOnKeyListener(new Dialog.OnKeyListener() {
            //按下返回键调用dismiss

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    dismiss();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public int getHeight() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (onBaseDialogFragmentCallBack != null) {
            onBaseDialogFragmentCallBack.onShowComplete();
        }
    }

    /**
     * 获取点击其他区域是否关闭
     *
     * @return  boolean
     */
    public boolean getCancelOutside() {
        return false;
    }

    /**
     * 获取遮罩透明度
     *
     * @return  float
     */
    public float getDimAmount() {
        return DEFAULT_MASK_TRANS;
    }


    public boolean isGravity() {
        return true;
    }


    /**
     * 获取对齐方式
     *
     * @return  int
     */
    public int getGravity() {
        return this.mGravity;
    }

    /**
     * 获取tag
     *
     * @return  String
     */
    public String getFragmentTag() {
        return TAG;
    }

    /**
     * 带回调的显示dialog
     *
     * @param fragmentManager   FragmentManager
     * @param onBaseDialogFragmentCallBack  OnBaseDialogFragmentCallBack
     */
    public void show(FragmentManager fragmentManager, OnBaseDialogFragmentCallBack onBaseDialogFragmentCallBack) {
        this.onBaseDialogFragmentCallBack = onBaseDialogFragmentCallBack;
        show(fragmentManager);

    }

    public void show(FragmentManager manager, Class cls) {
        if (canShow(manager)) {
            super.show(manager, cls.getSimpleName() + System.currentTimeMillis());
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (canShow(manager)) {
            super.show(manager, tag + System.currentTimeMillis());
        }
    }

    /**
     * 显示dialog
     *
     * @param fragmentManager   FragmentManager
     * @return 显示成功返回true, 失败false
     */
    public boolean show(FragmentManager fragmentManager) {
        if (canShow(fragmentManager)) {
            super.show(fragmentManager, getFragmentTag() + System.currentTimeMillis());
            return true;
        }
        return false;
    }

    /**
     * 是否可以显示
     *
     * @param fragmentManager
     */
    long time = 0;

    private boolean canShow(FragmentManager fragmentManager) {
        if (time != 0) {
            if (System.currentTimeMillis() - time < 1000) {//1秒内只能显示一次，防止高并发操作导致王炸
                return false;
            }
        }
        if (!isAdded() && !isVisible() && !isRemoving()) {
            if (fragmentManager.findFragmentByTag(getFragmentTag()) == null) {
                time = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }


    public interface OnBaseDialogFragmentCallBack {

        /**
         * on Show Complete
         */
        void onShowComplete();
    }
}
