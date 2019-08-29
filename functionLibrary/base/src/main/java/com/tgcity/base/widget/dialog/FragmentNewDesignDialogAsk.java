package com.tgcity.base.widget.dialog;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tgcity.base.R;

/**
 * @author TGCity
 * 选择对话框
 */
public class FragmentNewDesignDialogAsk extends BaseDialogFragment {
    private TextView title, content, cancel, fix;
    private OnOperationCallBack onOperationCallBack;

    /**
     * 清理垃圾
     */
    public void clear() {
        title = null;
        content = null;
        cancel = null;
        fix = null;
        onOperationCallBack = null;
        dismiss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_new_design_dialog_ask;
    }

    @Override
    public float getDimAmount() {
        return 0.8f;
    }


    @Override
    public void bindView(View v) {
        title = v.findViewById(R.id.title);
        content = v.findViewById(R.id.content);
        cancel = v.findViewById(R.id.cancel);
        fix = v.findViewById(R.id.okey);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOperationCallBack != null) {
                    onOperationCallBack.onCancel(FragmentNewDesignDialogAsk.this);
                }
            }
        });
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOperationCallBack != null) {
                    onOperationCallBack.onFix(FragmentNewDesignDialogAsk.this);
                }
            }
        });
    }


    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getStyleRes() {
        return R.style.my_dialog;
    }



    public void show(FragmentManager fragmentManager, OnOperationCallBack operationCallBack, final OnAskShowCallBack onAskShowCallBack) {
        this.onOperationCallBack = operationCallBack;
        super.show(fragmentManager, new OnBaseDialogFragmentCallBack() {
            @Override
            public void onShowComplete() {
                getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                if (onAskShowCallBack != null) {
                    onAskShowCallBack.onShowComplete(FragmentNewDesignDialogAsk.this, title, content, cancel, fix);
                }
            }
        });
    }

    public interface OnOperationCallBack {

        /**
         * cancel
         * @param fragmentNewDesignDialogAsk    FragmentNewDesignDialogAsk
         */
        void onCancel(FragmentNewDesignDialogAsk fragmentNewDesignDialogAsk);

        /**
         *  on fix
         * @param fragmentNewDesignDialogAsk FragmentNewDesignDialogAsk
         */
        void onFix(FragmentNewDesignDialogAsk fragmentNewDesignDialogAsk);
    }

    public interface OnAskShowCallBack {

        /**
         * Show Complete
         * @param fragmentNewDesignDialogAsk    FragmentNewDesignDialogAsk
         * @param title TextView
         * @param content   TextView
         * @param cancel    TextView
         * @param fix  TextView
         */
        void onShowComplete(FragmentNewDesignDialogAsk fragmentNewDesignDialogAsk, TextView title, TextView content, TextView cancel, TextView fix);
    }
}
