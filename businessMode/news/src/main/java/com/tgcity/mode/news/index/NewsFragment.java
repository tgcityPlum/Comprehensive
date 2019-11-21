package com.tgcity.mode.news.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.base.constant.RouteConstant;
import com.tgcity.mode.news.R;
import com.tgcity.mode.news.utils.NewsUtils;
import com.tgcity.network.base.NetworkConstant;

/**
 * @author TGCity
 */
@Route(path = RouteConstant.NewsMode.NEWS_FRAGMENT)
public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_fragment_main, null);
        NewsUtils.initNetWork(getContext(),false, NetworkConstant.ServiceFlag.SERVER_DEFAULT);
        return root;
    }

}
