package com.tgcity.demo.testfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tgcity.base.constant.ARouteConstant;
import com.tgcity.demo.R;
import com.tgcity.demo.util.DrawableUtil;
import com.tgcity.mode.index.HomeFragment;
import com.tgcity.mode.news.testfragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class TestFragmentActivity extends AppCompatActivity {

    private RecyclerView mRvBottom;
    private BottomAdapter mAdapter;
    private List<TabTitle> titleObjectList = new ArrayList<>();//底部TAB的数据

    private FragmentManager fm;

    private int oldPosition = 0;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        //定义控件
        initViewById();
        //定义底部控件
        initBottom();
        //定义fragment
        initFragments();
        //定义fragment管理器
        initFragmentTransaction();
    }

    private void initFragmentTransaction() {
        fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // 使用homeFragment 替换 content_layout 布局
        fragmentTransaction.replace(R.id.content_frame, fragmentList.get(0));
        fragmentTransaction.commit();
    }

    private void initFragments() {
        NewsFragment newsFragment = (NewsFragment) ARouter.getInstance().build(titleObjectList.get(0).getRoutePath()).navigation();
        AppFragment appFragment = (AppFragment) ARouter.getInstance().build(titleObjectList.get(1).getRoutePath()).navigation();
        HomeFragment homeFragment = (HomeFragment) ARouter.getInstance().build(titleObjectList.get(2).getRoutePath()).navigation();

        fragmentList.add(newsFragment);
        fragmentList.add(appFragment);
        fragmentList.add(homeFragment);
    }

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
                ARouteConstant.NewsMode.MAIN_FRAGMENT,
                R.string.tag_name_tab1,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab1, R.mipmap.a_tabbar_home_p)));

        titleObjectList.add(new TabTitle(
                ARouteConstant.AppMode.MAIN_FRAGMENT,
                R.string.tag_name_tab2,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab3, R.mipmap.a_tabbar_market_p)));

        titleObjectList.add(new TabTitle(
                ARouteConstant.HomeMode.MAIN_FRAGMENT,
                R.string.tag_name_tab3,
                R.color.home_tab_text_selector,
                DrawableUtil.getStateListDrawable(this, R.mipmap.a_tabbar_tab3, R.mipmap.a_tabbar_market_p)));

        return titleObjectList;
    }

}
