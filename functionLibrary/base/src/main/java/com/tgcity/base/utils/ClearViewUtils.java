package com.tgcity.base.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 清理控件的工具类
 * @author TGCity
 */
public class ClearViewUtils {
    /**
     * 清理各种View中子View的资源、回调、数据或引用等，可扩展
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void clearAll(View target) {
        //上来首先无脑切断背景及各种引用
        clearListener(target);
        if (target.getBackground() != null) {
            target.getBackground().setCallback(null);
            target.setBackgroundColor(0);
            target.setBackground(null);
            target.setBackgroundResource(0);
        }
        if (target instanceof ViewGroup) {
            ViewGroup targetGroup = (ViewGroup) target;
            for (int i = 0; i < targetGroup.getChildCount(); i++) {
                //如果目标View满足以下指定的view的，执行相应view内部的clear方法或相应的清理逻辑，否则，按照LinearLayout、FrameLayout、RelativeLayout等系统布局直接removeAllViews处理
                LogUtils.i("清理垃圾---当前清理的View：" + targetGroup.getChildAt(i).getClass().getSimpleName() + "，此View位于布局" + targetGroup.getClass().getSimpleName() + "的第" + i + "层");
                clearListener(targetGroup.getChildAt(i));
                if (targetGroup.getChildAt(i) instanceof ImageView) {
                    //TODO 系统图片框
                    ImageView imageView = (ImageView) targetGroup.getChildAt(i);
                    releaseImageViewResource((ImageView) imageView, targetGroup);
                } else if (targetGroup.getChildAt(i) instanceof TextView) {
                    //TODO 标签
                    TextView textView = (TextView) targetGroup.getChildAt(i);
                    textView.setText(null);
                    textView.clearComposingText();
                } else if ((targetGroup.getChildAt(i) instanceof WebView)) {
                    //TODO WebView网页
                    WebView webView = (WebView) targetGroup.getChildAt(i);
                    webView.clearHistory();
                    webView.clearCache(true);
                    webView.loadUrl("about:blank");
                    webView.freeMemory();
                    webView.pauseTimers();
                    webView.clearFormData();
                    webView.clearSslPreferences();
                    webView.removeAllViews();
                    webView.destroy();
                } /*else if (targetGroup.getChildAt(i) instanceof TitleBar) {
                    //TODO 标题导航栏
                    TitleBar titleBar = (TitleBar) targetGroup.getChildAt(i);
                    titleBar.clear();
                } else if (targetGroup.getChildAt(i) instanceof MarqueeTextView) {
                    //TODO 跑马灯
                    MarqueeTextView marqueeTextView = (MarqueeTextView) targetGroup.getChildAt(i);
                    marqueeTextView.clear();
                    marqueeTextView.setText("");
                    marqueeTextView.clearComposingText();
                } else if (targetGroup.getChildAt(i) instanceof TabLayout) {
                    //TODO tab切换
                    TabLayout tabLayout = (TabLayout) targetGroup.getChildAt(i);
                    tabLayout.clearOnTabSelectedListeners();
                    tabLayout.removeAllTabs();

                } else if (targetGroup.getChildAt(i) instanceof TagCloudView) {
                    //TODO 流式标签视图
                    TagCloudView tagCloudView = (TagCloudView) targetGroup.getChildAt(i);
                    tagCloudView.clear();
                } else if (targetGroup.getChildAt(i) instanceof CollapsedTextView) {
                    //TODO 折叠标签
                    CollapsedTextView collapsedTextView = (CollapsedTextView) targetGroup.getChildAt(i);
                    collapsedTextView.setText(null);
                    collapsedTextView.clearComposingText();
                    collapsedTextView.clear();
                } else if (targetGroup.getChildAt(i) instanceof StrongButton) {
                    //TODO 自定义Button
                    StrongButton strongButton = (StrongButton) targetGroup.getChildAt(i);
                    strongButton.clear();
                } else if (targetGroup.getChildAt(i) instanceof SimpleVideoPlayer) {
                    //TODO 自定义Button
                    SimpleVideoPlayer simpleVideoPlayer = (SimpleVideoPlayer) targetGroup.getChildAt(i);
                    simpleVideoPlayer.onDestroy();
                } else if (targetGroup.getChildAt(i) instanceof ImageTextView) {
                    //TODO 图片TextView
                    ImageTextView circleIndicator = (ImageTextView) targetGroup.getChildAt(i);
                    circleIndicator.clearImages();
                } else if (targetGroup.getChildAt(i) instanceof SimpleBanner) {
                    //TODO banner
                    SimpleBanner simpleBanner = (SimpleBanner) targetGroup.getChildAt(i);
                    simpleBanner.clear();
                } else if (targetGroup.getChildAt(i) instanceof BGABanner) {
                    //TODO Banner
                    BGABanner bgaBanner = (BGABanner) targetGroup.getChildAt(i);
                    bgaBanner.removePlaceholder();
                } else if (targetGroup.getChildAt(i) instanceof SimpleBanner) {
                    //TODO 全局通用的支持高斯模糊的无权限遮挡布局
                    NoPermissionView noPermissionView = (NoPermissionView) targetGroup.getChildAt(i);
                    noPermissionView.clear();
                } else if (targetGroup.getChildAt(i) instanceof SplashView) {
                    //TODO 启动页广告控件
                    SplashView splashView = (SplashView) targetGroup.getChildAt(i);
                    splashView.clear();
                } else if (targetGroup.getChildAt(i) instanceof SpringView) {
                    //TODO 下拉刷新控件
                    SpringView springView = (SpringView) targetGroup.getChildAt(i);
                    springView.clear();
                } else if (targetGroup.getChildAt(i) instanceof SimpleLineChart) {
                    //TODO 线条图
                    SimpleLineChart simpleLineChart = (SimpleLineChart) targetGroup.getChildAt(i);
                    simpleLineChart.clear();
                } else if (targetGroup.getChildAt(i) instanceof SimpleLineChart) {
                    //TODO 倒计时
                    CountdownView countdownView = (CountdownView) targetGroup.getChildAt(i);
                    countdownView.stop();
                } else if (targetGroup.getChildAt(i) instanceof ProgressSearchView) {
                    //TODO 带搜索的状态页
                    ProgressSearchView progressSearchView = (ProgressSearchView) targetGroup.getChildAt(i);
                    progressSearchView.clear();
                } else if (targetGroup.getChildAt(i) instanceof ProgressView) {
                    //TODO 状态页
                    ProgressView progressView = (ProgressView) targetGroup.getChildAt(i);
                    progressView.clear();
                } else if (targetGroup.getChildAt(i) instanceof ProgressActivity) {
                    //TODO 状态页父类
                    ProgressActivity progressActivity = (ProgressActivity) targetGroup.getChildAt(i);
                    progressActivity.clear();
                } else if (targetGroup.getChildAt(i) instanceof StudyTourProjectItem) {
                    LogUtils.e("游学档期item");
                    //TODO 游学档期item
                    StudyTourProjectItem studyTourProjectItem = (StudyTourProjectItem) targetGroup.getChildAt(i);
                    studyTourProjectItem.clear();

                } else if ((targetGroup.getChildAt(i) instanceof WebViewLayout)) {
                    //TODO 定制的全局WebView，说白了就是上面的WebView网页
                    WebViewLayout webViewLayout = (WebViewLayout) targetGroup.getChildAt(i);
                    webViewLayout.clear();
                } else if ((targetGroup.getChildAt(i) instanceof SimpleParallaxRecycleView)) {
                    //TODO 视差RecyclerView
                    SimpleParallaxRecycleView simpleParallaxRecycleView = (SimpleParallaxRecycleView) targetGroup.getChildAt(i);
                    simpleParallaxRecycleView.clear();
                } else if ((targetGroup.getChildAt(i) instanceof SimpleParallaxRecycleView)) {
                    //TODO 滚动获取滑动量与渐变透明度RecyclerView
                    SlideMomentumRecyclerView slideMomentumRecyclerView = (SlideMomentumRecyclerView) targetGroup.getChildAt(i);
                    slideMomentumRecyclerView.clear();
                    slideMomentumRecyclerView.getRecycledViewPool().clear();
                    slideMomentumRecyclerView.clearOnChildAttachStateChangeListeners();
                    slideMomentumRecyclerView.clearDisappearingChildren();
                    slideMomentumRecyclerView.clearAnimation();
                    slideMomentumRecyclerView.clearFocus();
                    if (slideMomentumRecyclerView.getAdapter() != null) {
                        if (slideMomentumRecyclerView.getAdapter() instanceof BaseQuickAdapter) {
                            BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) slideMomentumRecyclerView.getAdapter();
                            if (baseQuickAdapter.getData() != null) {
                                LogUtils.i("清理垃圾---正在清理" + slideMomentumRecyclerView.getAdapter().getClass().getSimpleName() + "适配器中的垃圾");
                                baseQuickAdapter.setEnableLoadMore(false);
                                baseQuickAdapter.setHeaderFooterEmpty(false, false);
                                baseQuickAdapter.getData().clear();
                                baseQuickAdapter.setOnLoadMoreListener(null, null);
                                baseQuickAdapter.setOnItemChildClickListener(null);
                                baseQuickAdapter.setOnItemChildLongClickListener(null);
                                baseQuickAdapter.setOnItemClickListener(null);
                                baseQuickAdapter.setOnItemLongClickListener(null);
                            }
                        }
                    }
                } else if (targetGroup.getChildAt(i) instanceof RecyclerView) {
                    //TODO RecyclerView列表
                    RecyclerView recyclerView = (RecyclerView) targetGroup.getChildAt(i);
                    recyclerView.getRecycledViewPool().clear();
                    recyclerView.clearOnChildAttachStateChangeListeners();
                    recyclerView.clearDisappearingChildren();
                    recyclerView.clearAnimation();
                    recyclerView.clearFocus();
                    if (recyclerView.getAdapter() != null) {
                        if (recyclerView.getAdapter() instanceof BaseQuickAdapter) {
                            BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) recyclerView.getAdapter();
                            if (baseQuickAdapter.getData() != null) {
                                LogUtils.i("清理垃圾---正在清理" + recyclerView.getAdapter().getClass().getSimpleName() + "适配器中的垃圾");
                                baseQuickAdapter.setEnableLoadMore(false);
                                baseQuickAdapter.setHeaderFooterEmpty(false, false);
                                baseQuickAdapter.getData().clear();
                                baseQuickAdapter.setOnLoadMoreListener(null, null);
                                baseQuickAdapter.setOnItemChildClickListener(null);
                                baseQuickAdapter.setOnItemChildLongClickListener(null);
                                baseQuickAdapter.setOnItemClickListener(null);
                                baseQuickAdapter.setOnItemLongClickListener(null);
                                baseQuickAdapter.setOnBindRecyclerViewCallBack(null);
                            }
                        }
//                        for (int j = 0; j < recyclerView.getChildCount(); j++) {
//                            releaseViewDrawable(recyclerView.getChildAt(j));
//                        }
                    }

                }*/

                if ((targetGroup.getChildAt(i)) != null) {
                    clearAll((targetGroup.getChildAt(i)));
                }
            }
            if (!(targetGroup instanceof AdapterView)) {//AdapterView 不能removeAllViews,否则直接抛异常
                targetGroup.removeAllViews();
            }

            if (targetGroup.getBackground() != null) {
                targetGroup.getBackground().setCallback(null);
            }
            targetGroup = null;
        }
        target = null;
    }

    /**
     * 清理指定View的各种事件
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void clearListener(View view) {
        if (view == null) {
            return;
        }

        try {//防止某些机型SDK被改动过而找不到方法
            if (!(view instanceof AdapterView)) {//AdapterView 不能设置 onClickListener,否则直接抛异常
                view.setOnClickListener(null);
            }
            view.setOnFocusChangeListener(null);
            view.setOnTouchListener(null);
            view.setOnScrollChangeListener(null);
            view.setOnContextClickListener(null);
            view.setOnLongClickListener(null);
            view.setOnDragListener(null);
            view.setOnSystemUiVisibilityChangeListener(null);
            view.setOnApplyWindowInsetsListener(null);
            view.setOnCapturedPointerListener(null);
            view.setOnCreateContextMenuListener(null);
            view.setOnHoverListener(null);
            view.setOnKeyListener(null);
            view.setOnGenericMotionListener(null);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放imageview中的图片
     *
     * @param imageView 要被释放的imageview
     * @param viewGroup 要被释放的imageview的父容器
     */
    public static void releaseImageViewResource(ImageView imageView, ViewGroup viewGroup) {
        if (imageView == null) {
            return;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
//            if (bitmap != null && !bitmap.isRecycled()) {//会导致正在使用中的bitmap也会被清理掉
//                bitmap.recycle();
//            }
            bitmap = null;
            bitmapDrawable = null;
            //将imageView从父容器移除，并将imag置为null
            if (viewGroup != null && imageView != null) {
                viewGroup.removeView(imageView);
                imageView.clearColorFilter();
                imageView.setImageDrawable(null);
                imageView.setImageBitmap(null);
                imageView = null;
            }
            drawable.setCallback(null);
        }
    }

}
