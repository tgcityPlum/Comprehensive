package com.tgcity.mode.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.activity.BaseCommonActivity;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.mode.home.index.HomeFragment;
import com.tgcity.mode.news.testfragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * main模块--主页
 */
@Route(path = RouteConstant.MainMode.MAIN_FRAGMENT)
public class MainCoreActivity extends BaseCommonActivity {
    //底部列表控件
    private RecyclerView mRvBottom;
    //列表适配器
    private BottomAdapter mAdapter;
    //标题列表
    private List<TabTitle> titleObjectList = new ArrayList<>();//底部TAB的数据
    //FragmentManager
    private FragmentManager fm;
    //fragment的visible位置
    private int oldPosition = 0;
    //fragment集合列表
    private List<Fragment> fragmentList = new ArrayList<>();

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
        NewsFragment newsFragment = (NewsFragment) ARouter.getInstance().build(titleObjectList.get(1).getRoutePath()).navigation();

        fragmentList.add(homeFragment);
        fragmentList.add(newsFragment);
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
     * @return
     */
    private List<TabTitle> getBottomSetting() {
        //本来这里应该要读取配置文件，然后配置文字和颜色，还有图标,暂时写死
        titleObjectList.add(new TabTitle(
                RouteConstant.HomeMode.MAIN_FRAGMENT,
                R.string.tag_name_tab3,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab1, R.mipmap.a_tabbar_home_p)));

        titleObjectList.add(new TabTitle(
                RouteConstant.NewsMode.MAIN_FRAGMENT,
                R.string.tag_name_tab1,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab2, R.mipmap.a_tabbar_trade_p)));

        /*titleObjectList.add(new TabTitle(
                RouteConstant.AppMode.MAIN_FRAGMENT,
                R.string.tag_name_tab2,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab3, R.mipmap.a_tabbar_market_p)));
*/
        return titleObjectList;
    }

}
