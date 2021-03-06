package com.tgcity.mode.home.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tgcity.mode.arouter.constant.RouteConstant;
import com.tgcity.mode.home.R;

/**
 * @author TGCity
 * home mode index fragment
 */
@Route(path = RouteConstant.HomeMode.HOME_FRAGMENT)
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment_main, null);

        return root;
    }

}
