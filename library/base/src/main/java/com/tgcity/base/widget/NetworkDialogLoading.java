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

public class NetworkDialogLoading extends Dialog {
    private ImageView loading_progress;
    private Context mContext;
    private TextView loadingText;

    public void clear() {
        mContext = null;
        loadingText = null;
        loading_progress = null;
    }

    public NetworkDialogLoading(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

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

    protected NetworkDialogLoading(Context context, boolean cancelable,
                                   OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public NetworkDialogLoading(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_base_loading);
        loading_progress = (ImageView) findViewById(R.id.loading_pic);
        loadingText = (TextView) findViewById(R.id.loading_text);
        try {
            Glide.with(mContext).load(R.drawable.loading_dh).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).thumbnail(1.0f).into(loading_progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
