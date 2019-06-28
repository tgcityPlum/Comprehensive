package com.tgcity.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tgcity.base.R;

public class BaseLoadingDialog extends Dialog {
    //loading image
    private ImageView loadingProgress;
    //loading context
    private TextView loadingText;
    //context
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_base_loading);
        loadingProgress = findViewById(R.id.loading_pic);
        loadingText = findViewById(R.id.loading_text);
        try {
            Glide.with(mContext)
                    .load(R.drawable.loading_dh)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .thumbnail(1.0f)
                    .into(loadingProgress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseLoadingDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseLoadingDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    protected BaseLoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    /**
     * show dialog
     */
    public void show() {
        if (!isShowing()) {
            try {
                super.show();
                setCanceledOnTouchOutside(false);
            } catch (Exception e) {
                dismiss();
            }
        }
    }

    /**
     * upData context
     *
     * @param info context
     */
    public void updateText(String info) {
        if (loadingText != null) {
            loadingText.setText(info);
        }
    }


    /**
     * clear view
     */
    public void clear() {
        mContext = null;
        loadingText = null;
        loadingProgress = null;
    }
}
