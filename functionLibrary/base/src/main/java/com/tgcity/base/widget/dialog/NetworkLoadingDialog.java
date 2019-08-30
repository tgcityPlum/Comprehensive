package com.tgcity.base.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tgcity.base.R;

/**
 * @author TGCity
 */
public class NetworkLoadingDialog extends Dialog {
    private ImageView loadingProgress;
    private Context mContext;
    private TextView loadingText;

    public NetworkLoadingDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public NetworkLoadingDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    protected NetworkLoadingDialog(Context context, boolean cancelable,
                                   OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_base_loading);
        loadingProgress = findViewById(R.id.loading_pic);
        loadingText = findViewById(R.id.loading_text);
        try {
            Glide.with(mContext).load(R.drawable.loading_dh).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).thumbnail(1.0f).into(loadingProgress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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

    public void updateText(String info) {
        if (loadingText != null) {
            loadingText.setText(info);
        }
    }

    public void clear() {
        mContext = null;
        loadingText = null;
        loadingProgress = null;
    }

}
