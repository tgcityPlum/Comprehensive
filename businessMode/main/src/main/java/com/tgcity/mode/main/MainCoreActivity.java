package com.tgcity.mode.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.base.widget.dialog.SelectionFragmentDialog;
import com.tgcity.mode.home.index.HomeFragment;
//import com.tgcity.mode.news.index.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TGCity
 * main模块--主页
 */
@Route(path = RouteConstant.MainMode.MAIN_ACTIVITY_CORE)
public class MainCoreActivity extends BaseCommonActivity {
    /**
     * 底部列表控件
     */
    private RecyclerView mRvBottom;
    /**
     * 列表适配器
     */
    private BottomAdapter mAdapter;
    /**
     * 标题列表
     * 底部TAB的数据
     */
    private List<TabTitle> titleObjectList = new ArrayList<>();

    private FragmentManager fm;
    /**
     * fragment的visible位置
     */
    private int oldPosition = 0;
    /**
     * fragment集合列表
     */
    private List<Fragment> fragmentList = new ArrayList<>();
    /**
     * 第一次点击时间
     */
    private long firstTime = 0;

    /**
     * click back type
     * 0: direct quit app
     * 1: quit app with toast
     * 2: quit app with dialog
     */
    private int clickBackType = BaseConstant.ItemType.LEVEL_1;

    /**
     * quit app dialog
     */
    private SelectionFragmentDialog quitAppDialog;

    @Override
    public int getViewLayout() {
        return R.layout.main_activity_core;
    }

    @Override
    public void initView() {
        initViewById();
        initBottom();
        initFragments();
        initFragmentTransaction();
    }

    @Override
    public String getCurrentPage() {
        return getLocalClassName();
    }

    /**
     * 定义fragment管理器
     */
    private void initFragmentTransaction() {
        fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // 使用homeFragment 替换 content_layout 布局
        fragmentTransaction.replace(R.id.content_frame, fragmentList.get(0));
        fragmentTransaction.commit();
    }

    /**
     * 定义fragment
     */
    private void initFragments() {
        HomeFragment homeFragment = (HomeFragment) ARouter.getInstance().build(titleObjectList.get(0).getRoutePath()).navigation();
//        NewsFragment newsFragment = (NewsFragment) ARouter.getInstance().build(titleObjectList.get(1).getRoutePath()).navigation();

        fragmentList.add(homeFragment);
//        fragmentList.add(newsFragment);
    }

    /**
     * 定义控件
     */
    private void initViewById() {
        mRvBottom = findViewById(R.id.rv_bottom);
    }

    /**
     * 初始化底部
     */
    private void initBottom() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvBottom.setLayoutManager(linearLayoutManager);

        mAdapter = new BottomAdapter(this, getBottomSetting());
        mAdapter.setOnItemClickListener((position, fragmentRoutePath) -> {
            mAdapter.setSelection(position);
            if (!TextUtils.isEmpty(fragmentRoutePath)) {

                if (position != oldPosition) {
                    FragmentTransaction ft = fm.beginTransaction();
                    oldPosition = position;
                    if (fragmentList.get(oldPosition) != null) {
                        ft.replace(R.id.content_frame, fragmentList.get(oldPosition)).commit();
                    }
                }
            }
        });
        mRvBottom.setAdapter(mAdapter);
    }

    /**
     * 导航栏配置
     *
     * @return List
     */
    private List<TabTitle> getBottomSetting() {
        //本来这里应该要读取配置文件，然后配置文字和颜色，还有图标,暂时写死
        titleObjectList.add(new TabTitle(
                RouteConstant.HomeMode.HOME_FRAGMENT,
                R.string.tag_name_tab3,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(getApplicationContext(), R.mipmap.a_tabbar_tab1, R.mipmap.a_tabbar_home_p)));

        titleObjectList.add(new TabTitle(
                RouteConstant.NewsMode.NEWS_FRAGMENT,
                R.string.tag_name_tab1,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(getApplicationContext(), R.mipmap.a_tabbar_tab2, R.mipmap.a_tabbar_trade_p)));

        return titleObjectList;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (clickBackType == BaseConstant.ItemType.LEVEL_1) {
                quitAppWithToast();
                return true;
            }

            if (clickBackType == BaseConstant.ItemType.LEVEL_2) {
                quitAppWithDialog();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    private void quitAppWithDialog() {
        if (quitAppDialog == null) {
            quitAppDialog = new SelectionFragmentDialog();
        }
        quitAppDialog.show(getSupportFragmentManager(), new SelectionFragmentDialog.OnOperationCallBack() {
            @Override
            public void onCancel(SelectionFragmentDialog selectionDialogFragment) {
                selectionDialogFragment.dismiss();
            }

            @Override
            public void onFix(SelectionFragmentDialog selectionDialogFragment) {
                selectionDialogFragment.dismissAllowingStateLoss();
                finish();
            }
        }, (selectionDialogFragment, title, content, cancel, fix) -> {
            title.setText(getString(R.string.dialog_title));
            content.setText(getString(R.string.exit_app));
            fix.setText(getString(R.string.dialog_fix));
            cancel.setText(getString(R.string.dialog_cancel));
        });
    }

    private void quitAppWithToast() {
        long secondTime = System.currentTimeMillis();
        long intervalTime = 2000;
        if (secondTime - firstTime < intervalTime) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.exit_app), Toast.LENGTH_SHORT).show();
            firstTime = System.currentTimeMillis();
        }
    }
}
