package com.tgcity.refreshview.springview.container;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.refreshview.R;
import com.tgcity.refreshview.springview.widget.SpringView;

/**
 * Created by Administrator on 2016/3/21.
 */
public class DefaultFooter extends BaseFooter {
    private Context context;
    private int rotationSrc;
    private TextView footerTitle;
    private ProgressBar footerProgressbar;

    @Override
    public void clear() {
        context = null;
        rotationSrc = 0;
        footerTitle = null;
        footerProgressbar = null;
    }

    public DefaultFooter(Context context) {
        this(context, R.drawable.progress_small);
    }

    public DefaultFooter(Context context, int rotationSrc) {
        this.context = context;
        this.rotationSrc = rotationSrc;
    }

    @Override
    public void onPreMove(View rootView) {

    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.default_footer, viewGroup, true);
        footerTitle = (TextView) view.findViewById(R.id.default_footer_title);
        footerProgressbar = (ProgressBar) view.findViewById(R.id.default_footer_progressbar);
        footerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context, rotationSrc));
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onFingerRemoval(View rootView) {

    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (upORdown) {
            footerTitle.setText("松开载入更多");
        } else {
            footerTitle.setText("查看更多");
        }
    }

    @Override
    public void onStartAnim() {
        footerTitle.setVisibility(View.INVISIBLE);
        footerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        footerTitle.setText("查看更多");
        footerTitle.setVisibility(View.VISIBLE);
        footerProgressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFinishResetAnim() {

    }

    @Override
    public void onMoving() {

    }

    @Override
    public SpringView.DragHander setOnExceptionEventClickListener(SpringView.OnExceptionEventClickListener onExceptionEventClickListener) {
        return this;
    }

    @Override
    public void result(ErrorMode o) {

    }

    @Override
    public void setErrorImg(int res) {

    }

    @Override
    public void setErrorTitleAndDesc(String title, String desc) {

    }


}