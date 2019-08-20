package com.tgcity.refreshview.header;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tgcity.base.utils.check.AntiShake;
import com.tgcity.base.utils.imageloader.ImageHelper;
import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.refreshview.R;
import com.tgcity.refreshview.springview.container.BaseHeader;
import com.tgcity.refreshview.springview.widget.SpringView;

/**
 * Created by Administrator on 2016/3/21.
 */
public class RefreshHeader extends BaseHeader {
    private Context context;
    private int rotationSrc;
    private int arrowSrc;

    private long freshTime;

    private int ROTATE_ANIM_DURATION = 180;
    private RotateAnimation mRotateUpAnim;
    private RotateAnimation mRotateDownAnim;

    private TextView headerTitle;
    private TextView headerTime;
    private ImageView headerArrow;
    private ProgressBar headerProgressbar;

    private RelativeLayout parentNormalHeader;
    private ConstraintLayout parentExceptionHeader;

    private ImageView errorLogo;
    private TextView errorTitle, errorDesc;

    private int errorImg = R.mipmap.ic_error;
    private String errorTxtTitle = "出错啦", errorDescTitle = "轻触重试";

    private SpringView.OnExceptionEventClickListener onExceptionEventClickListener;

    private ErrorMode errorMode;

    @Override
    public void clear() {
        context = null;
        rotationSrc = 0;
        arrowSrc = 0;

        freshTime = 0;

        ROTATE_ANIM_DURATION = 0;
        if (mRotateUpAnim != null) {
            mRotateUpAnim.cancel();
        }
        mRotateUpAnim = null;
        if (mRotateDownAnim != null) {
            mRotateDownAnim.cancel();
        }
        mRotateDownAnim = null;
        headerTitle = null;
        headerTime = null;
        headerArrow = null;
        headerProgressbar = null;

        parentNormalHeader = null;
        parentExceptionHeader = null;

        errorLogo = null;
        errorTitle = null;
        errorDesc = null;

        errorImg = 0;
        errorTxtTitle = null;
        errorDescTitle = null;

        onExceptionEventClickListener = null;

        errorMode = null;
    }

    public RefreshHeader(Context context) {
        this(context, R.drawable.progress_small, R.drawable.arrow);
    }

    public RefreshHeader(Context context, int rotationSrc, int arrowSrc) {
        this.context = context;
        this.rotationSrc = rotationSrc;
        this.arrowSrc = arrowSrc;

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.refresh_header, viewGroup, true);
        headerTitle = (TextView) view.findViewById(R.id.default_header_title);
        headerTime = (TextView) view.findViewById(R.id.default_header_time);
        headerArrow = (ImageView) view.findViewById(R.id.default_header_arrow);
        headerProgressbar = (ProgressBar) view.findViewById(R.id.default_header_progressbar);
        errorLogo = (ImageView) view.findViewById(R.id.error_logo);
        errorTitle = (TextView) view.findViewById(R.id.error_title);
        errorDesc = (TextView) view.findViewById(R.id.error_desc);

        setLogo(errorImg);
        setExceptionInfo(errorTxtTitle, errorDescTitle);

        headerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context, rotationSrc));
        headerArrow.setImageResource(arrowSrc);
        parentNormalHeader = view.findViewById(R.id.parent_normal_header);
        parentExceptionHeader = view.findViewById(R.id.parent_exception_header);
        parentExceptionHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AntiShake.check(parentExceptionHeader.getId())) {
                    return;
                }
                if (onExceptionEventClickListener != null) {
                    onExceptionEventClickListener.onExceptionClick(errorMode, context);
                }
            }
        });
        result(null);
        return view;
    }

    /**
     * 设置出错图标
     *
     * @param res
     */
    public void setLogo(int res) {
        ImageHelper.display(errorLogo,res > 0 ? res : errorImg);
    }

    /**
     * 设置错误信息
     *
     * @param msg
     * @param desc
     */
    public void setExceptionInfo(String msg, String desc) {
        errorTitle.setText(msg == null ? "出错啦" : msg);
        errorDesc.setText(desc == null ? "轻触重试" : desc);
    }

    /**
     * 显示刷新结果，obj为空为正常，否则为异常，可执行异常逻辑
     *
     * @param errorMode
     */
    @Override
    public void result(ErrorMode errorMode) {
        this.errorMode = errorMode;
        if (null == errorMode) {
            parentNormalHeader.setVisibility(View.VISIBLE);
            parentExceptionHeader.setVisibility(View.GONE);
        } else {
            parentNormalHeader.setVisibility(View.GONE);
            parentExceptionHeader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setErrorImg(int res) {
        setLogo(errorImg);
    }

    @Override
    public void setErrorTitleAndDesc(String title, String desc) {
        setExceptionInfo(title, desc);
    }


    @Override
    public void onPreDrag(View rootView) {
        if (freshTime == 0) {
            freshTime = System.currentTimeMillis();
        } else {
            int m = (int) ((System.currentTimeMillis() - freshTime) / 1000 / 60);
            if (m >= 1 && m < 60) {
                headerTime.setText(m + "分钟前");
            } else if (m >= 60) {
                int h = m / 60;
                headerTime.setText(h + "小时前");
            } else if (m > 60 * 24) {
                int d = m / (60 * 24);
                headerTime.setText(d + "天前");
            } else if (m == 0) {
                headerTime.setText("刚刚");
            }
        }
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown) {
            headerTitle.setText("松开刷新");
            if (headerArrow.getVisibility() == View.VISIBLE)
            {
                headerArrow.startAnimation(mRotateUpAnim);
            }
        } else {
            headerTitle.setText("下拉刷新");
            if (headerArrow.getVisibility() == View.VISIBLE)
            {
                headerArrow.startAnimation(mRotateDownAnim);
            }
        }
    }

    @Override
    public void onStartAnim() {
        freshTime = System.currentTimeMillis();
        headerTitle.setText("正在刷新");
        headerArrow.setVisibility(View.INVISIBLE);
        headerArrow.clearAnimation();
        headerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        headerArrow.setVisibility(View.VISIBLE);
        headerProgressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFinishResetAnim() {

    }

    @Override
    public void onMoving() {

    }

    /**
     * 异常回调
     *
     * @param onExceptionEventClickListener
     */
    @Override
    public SpringView.DragHander setOnExceptionEventClickListener(SpringView.OnExceptionEventClickListener onExceptionEventClickListener) {
        this.onExceptionEventClickListener = onExceptionEventClickListener;
        return this;
    }


}