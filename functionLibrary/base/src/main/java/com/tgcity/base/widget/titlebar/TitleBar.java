package com.tgcity.base.widget.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgcity.base.R;
import com.tgcity.base.utils.LogUtils;

/**
 * 通用标题栏,支持XML中定制标题栏的功能,扩展了仿微信朋友圈toolbar轻触返回顶部
 * Created by Administrator on 2018/7/31.
 */

public class TitleBar extends Toolbar {
    private String doubleClickTip = "轻触此处返回顶部";//轻触返回到顶部提示语
    private int doubleClickTipValves = 500;//显示轻触返回到顶部提示语的阀值
    private ImageView back;//返回键
    private ImageView rightImgMenu;//右侧图形按钮
    private TextView rightTextMenu;//右侧文字按钮
    private int leftImgScaleType = -1;/**左侧图形按钮图标显示模式（值完全对应{@link ImageView.ScaleType},-1为默认）*/
    private int rightImgScaleType = -1;/**右侧图形按钮图标显示模式（值完全对应{@link ImageView.ScaleType},-1为默认）*/
    private TextView title;//标题
    private int backRes;//返回键底图
    private int rightImgMenuRes;//右侧图形按钮底图
    private String titleStr = "";//标题文字
    private String rightTextMenuStr;//右侧文字按钮标题
    private int rightStrColor = Color.parseColor("#8A000000");//右侧文字按钮颜色(android default textView textColor)
    private OnClickListener backListener;
    private OnClickListener rightMenuListener;
    private RecyclerView recyclerView;
    private int scrollY_old;//recyclerView上一次的滑动量
    private int scrollY_new;//recyclerView实时滑动量
    private boolean autoScrolling;//recyclerView是否处于自动滑动中
    private boolean isInit = false;

    public void clear() {
        title = null;
        if (back != null) {
            back.setOnClickListener(null);
        }
        back = null;
        if (rightImgMenu != null) {
            rightImgMenu.setOnClickListener(null);
        }
        rightImgMenu = null;
        if (rightTextMenu != null) {
            rightTextMenu.setOnClickListener(null);
        }
        rightTextMenuStr = null;
        titleStr = null;
        rightImgMenuRes = 0;
        backListener = null;
        rightMenuListener = null;
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(onScrollListener);
        }
        recyclerView = null;
        onScrollListener = null;
        isInit = false;
    }


    public void setBackListener(OnClickListener onClickListener) {
        backListener = onClickListener;
        initListener();
    }

    public void setMenuListener(OnClickListener onClickListener) {
        rightMenuListener = onClickListener;
        initListener();
    }

    public TextView getTitleView() {
        return title;
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public ImageView getBack() {
        return back;
    }

    public ImageView getRightImgMenu() {
        return rightImgMenu;
    }

    public TextView getRightTextMenu() {
        return rightTextMenu;
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        if (back != null) {
            if (backRes != 0) {
                back.setOnClickListener(backListener);
            } else {
                back.setOnClickListener(null);
            }
        }

        if (rightImgMenu != null) {
            if (rightImgMenuRes != 0) {
                rightImgMenu.setOnClickListener(rightMenuListener);
            } else {
                rightImgMenu.setOnClickListener(null);
            }
        }

        if (rightTextMenu != null) {
            if (rightTextMenuStr != null | !"".equals(rightTextMenuStr)) {
                rightTextMenu.setOnClickListener(rightMenuListener);
            } else {
                rightTextMenu.setOnClickListener(null);
            }
        }
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        backRes = typedArray.getResourceId(R.styleable.TitleBar_back, 0);
        rightImgMenuRes = typedArray.getResourceId(R.styleable.TitleBar_rightImgRes, 0);
        rightStrColor = typedArray.getColor(R.styleable.TitleBar_rightStrColor, 0) == 0 ? rightStrColor : typedArray.getColor(R.styleable.TitleBar_rightStrColor, 0);
        titleStr = typedArray.getString(R.styleable.TitleBar_title);
        doubleClickTip = typedArray.getString(R.styleable.TitleBar_doubleClickTip) == null ? doubleClickTip : typedArray.getString(R.styleable.TitleBar_doubleClickTip);
        rightTextMenuStr = typedArray.getString(R.styleable.TitleBar_rightStr);
        doubleClickTipValves = typedArray.getInt(R.styleable.TitleBar_doubleClickTipValves, doubleClickTipValves);
        leftImgScaleType=typedArray.getInt(R.styleable.TitleBar_leftImgScaleType, leftImgScaleType);
        rightImgScaleType=typedArray.getInt(R.styleable.TitleBar_rightImgScaleType, rightImgScaleType);
        typedArray.recycle();
        init();
    }

    /**
     * 清理垃圾
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        back = null;
        rightImgMenu = null;
        rightTextMenu = null;
        title = null;
        titleStr = null;
        rightTextMenuStr = null;
        backListener = null;
        rightMenuListener = null;
    }

    /**
     * 初始化
     */
    protected void init() {
        if (!isInit) {
            isInit = true;
            LayoutInflater.from(getContext()).inflate(R.layout.toolbar_layout, this);
            back = findViewById(R.id.leftBackImg);
            rightImgMenu = findViewById(R.id.rightImgMenu);
            rightTextMenu = findViewById(R.id.rightTextMenu);
            title = findViewById(R.id.title);
            title.setText(titleStr);
            rightTextMenu.setTextColor(rightStrColor);
            title.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isTipStatus()) {
                        if (recyclerView != null) {
                            resetTitle();
                            autoScrolling = true;
                            if (recyclerView.getChildCount() > 2) {
                                recyclerView.scrollToPosition(2);
                                recyclerView.smoothScrollToPosition(0);
                            }
                            recyclerView.smoothScrollToPosition(0);
                        }
                    }
                }
            });
            back.post(new Runnable() {
                @Override
                public void run() {
                    if (backRes != 0 && back != null) {
                        back.setImageResource(backRes);
                       setScaleType(leftImgScaleType,back);
                    }
                }
            });
            rightImgMenu.post(new Runnable() {
                @Override
                public void run() {
                    if (rightImgMenuRes != 0 && rightImgMenu != null) {
                        rightImgMenu.setImageResource(rightImgMenuRes);
                        setScaleType(leftImgScaleType,rightImgMenu);
                    }
                }
            });


            if (this.rightTextMenuStr != null) {
                rightTextMenu.setText(this.rightTextMenuStr);
            }
        }
        checkHideRightMenu();
    }

    /**
     * 设置图标显示类型
     */
    private void setScaleType(int scaleType,ImageView imageView){
        if (scaleType==0){
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }else  if (scaleType==1){
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }else  if (scaleType==2){
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        }else  if (scaleType==3){
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else  if (scaleType==4){
            imageView.setScaleType(ImageView.ScaleType.FIT_END);
        }else  if (scaleType==5){
            imageView.setScaleType(ImageView.ScaleType.CENTER);
        }else  if (scaleType==6){
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else  if (scaleType==7){
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    /**
     * 检查右边图形按钮与文字按钮是否冲突（优先显示图形按钮）
     */
    private void checkHideRightMenu() {
        if (this.rightImgMenuRes != 0) {
            if (rightImgMenu != null) {
                rightImgMenu.setVisibility(VISIBLE);
            }
            if (rightTextMenu != null) {
                rightTextMenu.setVisibility(INVISIBLE);
            }
            return;
        }

        if (this.rightTextMenuStr != null && !"".equals(this.rightTextMenuStr)) {
            if (rightTextMenu != null) {
                rightTextMenu.setVisibility(VISIBLE);
            }
            if (rightImgMenu != null) {
                rightImgMenu.setVisibility(INVISIBLE);
            }
            return;
        }
    }


    /**
     * 是否是提示语状态
     *
     * @return
     */
    public boolean isTipStatus() {
        return doubleClickTip.equals(title.getText().toString());
    }

    public String getTitleStr() {
        return titleStr;
    }

    /**
     * 设置标题
     *
     * @param titleStr
     */
    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;

        if (this.title != null) {
            this.title.setText(this.titleStr == null ? "" : this.titleStr);
        }
    }

    /**
     * 设置返回键图标
     *
     * @param backRes
     */
    public void setBackImgMenuRes(int backRes) {
        if (backRes == 0) {
            return;
        }
        if (this.backRes == backRes) {
            return;
        }
        this.backRes = backRes;
        if (back != null) {
            back.setImageResource(this.backRes);
        }
        init();
        initListener();
    }

    /**
     * 设置右侧图形按钮图标
     *
     * @param rightImgMenuRes
     */
    public void setRightImgMenuRes(int rightImgMenuRes) {
        if (rightImgMenuRes == 0) {
            return;
        }
        if (this.rightImgMenuRes == rightImgMenuRes) {
            return;
        }
        this.rightImgMenuRes = rightImgMenuRes;
        if (rightImgMenu != null) {
            rightImgMenu.setImageResource(this.rightImgMenuRes);
        }
        init();
        initListener();
    }

    /**
     * 设置右侧文字按钮标题
     *
     * @param rightTextMenuStr
     */
    public void setRightTextMenuStr(String rightTextMenuStr) {
        this.rightTextMenuStr = rightTextMenuStr;
        if (this.rightTextMenuStr != null && !"".equals(this.rightTextMenuStr) && rightTextMenu != null) {
            rightTextMenu.setText(this.rightTextMenuStr);
        }
        initListener();
    }

    /**
     * 设置右侧文字按钮颜色
     */
    public void setRightTextColor(int rightTextColor){
        rightStrColor=rightTextColor;
        rightTextMenu.setTextColor(rightStrColor);
    }

    /**
     * 设置右侧相关按钮的显隐
     */
    public void setRightTextMenuVisible(int visibility) {
        if (rightTextMenu != null) {
            rightTextMenu.setVisibility(visibility);
        }
    }

    /**
     * 设置右侧相关按钮的显隐
     */
    public void setRightImgMenuVisible(int visibility) {
        if (rightImgMenu != null) {
            rightImgMenu.setVisibility(visibility);
        }
    }

    /**
     * 设置中间标题的显隐
     *
     * @param visibility
     */
    public void setTitleVisible(int visibility) {
        if (title != null) {
            title.setVisibility(visibility);
        }
    }


    /**
     * 设置左侧返回键的显隐
     *
     * @param visibility
     */
    public void setLeftVisible(int visibility) {
        if (back != null) {
            back.setVisibility(visibility);
        }
    }

    /**
     * 设置标题的颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        if (title != null) {
            title.setTextColor(color);
        }
    }

    /**
     * 重置标题
     */
    public void resetTitle() {
        setTitleStr(titleStr);
    }

    /**
     * 绑定recyclerView(仿微信的轻触此处返回顶部)
     *
     * @param recyclerView
     */
    public void bindRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.addOnScrollListener(onScrollListener);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                autoScrolling = false;
            }
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
            if (firstCompletelyVisibleItemPosition == 0) {
                LogUtils.i("滑动到顶部");
                resetTitle();
            }

            int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1) {
                LogUtils.i("滑动到底部");
            }


        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            scrollY_old = scrollY_new;
            scrollY_new += dy;

            LogUtils.i("实时参数",
                    "是否是下拉：" + (scrollY_new < scrollY_old) + "---" +
                            "实时滑动量" + scrollY_new + "---" +
                            "上一次的滑动量" + scrollY_old + "---" +
                            "重滑计算值：" + (scrollY_new - scrollY_old) + "---" +
                            "是否是重滑" + (scrollY_new - scrollY_old < -Math.abs(doubleClickTipValves)));
            if (scrollY_new < scrollY_old) {//下拉
                if (scrollY_new - scrollY_old < -Math.abs(doubleClickTipValves)) {//两次滑动距离相减是否大于设定阀值，来判断用户是否是轻滑还是重滑,这个判断条件只有在下拉时才成立
                    if (!autoScrolling) {
                        title.setText(doubleClickTip);
                    }
                }
            } else {//上拉
                if (scrollY_new - scrollY_old > 8) {//设定一个缓冲值，否则向上重滑时用户的手指容易触发重置标题
                    resetTitle();
                }

            }
        }
    };


}
