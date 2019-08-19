package com.tgcity.refreshview.springview.container;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tgcity.network.cache.model.ErrorMode;
import com.tgcity.refreshview.R;
import com.tgcity.refreshview.springview.widget.SpringView;

public class DefaultHeader extends BaseHeader {
    private Context context;
    private int rotationSrc;
    private int arrowSrc;

    private boolean isRefresh;//是否处于刷新状态（判定规则：当手指触摸到控件并准备向下拖动的时候就开始算刷新状态，当手指放开且刷新动画运行完毕的时候则不在刷新状态）
    private long freshTime;

    private  int ROTATE_ANIM_DURATION = 180;
    private RotateAnimation mRotateUpAnim;
    private RotateAnimation mRotateDownAnim;

    private TextView headerTitle;
    private TextView headerTime;
    private ImageView headerArrow;
    private ProgressBar headerProgressbar;
    private OnChangeListener onChangeListener;

    @Override
    public void clear(){

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
        onChangeListener=null;

    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public DefaultHeader(Context context) {
        this(context, R.drawable.progress_small, R.drawable.arrow);
    }

    public DefaultHeader(Context context, int rotationSrc, int arrowSrc) {
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
        View view = inflater.inflate(R.layout.default_header, viewGroup, true);
        headerTitle = (TextView) view.findViewById(R.id.default_header_title);
        headerTime = (TextView) view.findViewById(R.id.default_header_time);
        headerArrow = (ImageView) view.findViewById(R.id.default_header_arrow);
        headerProgressbar = (ProgressBar) view.findViewById(R.id.default_header_progressbar);

        headerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context, rotationSrc));
        headerArrow.setImageResource(arrowSrc);
        return view;
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
        isRefresh = true;
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown) {
            headerTitle.setText("释放立即刷新…");
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
        headerTitle.setText("正在加载…");
        headerArrow.setVisibility(View.INVISIBLE);
        headerArrow.clearAnimation();
        headerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        headerTitle.setText("下拉刷新");
        headerArrow.setVisibility(View.VISIBLE);
        headerProgressbar.setVisibility(View.INVISIBLE);
        if (onChangeListener != null) onChangeListener.onFinishAnim();
    }

    @Override
    public void onFinishResetAnim() {
        if (onChangeListener != null) {
            onChangeListener.onFinishResetAnim();
        }
    }

    @Override
    public void onMoving() {
        if (onChangeListener!=null){
            onChangeListener.onMoving();
        }
    }

    @Override
    public void onPreMove(View rootView) {
        if (onChangeListener != null) {
            onChangeListener.onPreDrag();
        }
    }

    /**
     * 异常回调
     *
     * @param onExceptionEventClickListener
     */
    @Override
    public SpringView.DragHander setOnExceptionEventClickListener(SpringView.OnExceptionEventClickListener onExceptionEventClickListener) {
        return this;
    }

    /**
     * 显示刷新结果，obj为空为正常，否则为异常，可执行异常逻辑
     */
    @Override
    public void result(ErrorMode o) {

    }

    @Override
    public void setErrorImg(int res) {

    }

    @Override
    public void setErrorTitleAndDesc(String title, String desc) {

    }

    @Override
    public void onFingerRemoval(View rootView) {
        if (onChangeListener!=null){
            onChangeListener.onFingerRemoval();
        }
    }

    public interface OnChangeListener {

        /**
         * 准备拖动
         */
        void onPreDrag();

        void onMoving();

        /**
         * 刷新动画结束
         */
        void onFinishAnim();

        /**
         * 手指离开屏幕
         */
        void onFingerRemoval();

        /**
         * 回复动画结束
         */
        void onFinishResetAnim();
    }
}