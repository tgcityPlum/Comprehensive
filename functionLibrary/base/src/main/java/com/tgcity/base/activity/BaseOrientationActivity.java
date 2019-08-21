package com.tgcity.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 基础的activity类
 * --管理Activity的屏幕方向
 */
public abstract class BaseOrientationActivity extends BaseLifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            if (isOrientationPortrait()){
                setRequestedOrientation(getOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }finally {
            super.onCreate(savedInstanceState);
        }

    }

    /**
     * index back true to use ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     */
    public boolean isOrientationPortrait() {
        return true;
    }

    /**
     * the method for user can change the screen orientation
     * @param configOrientation screen orientation
     */
    public int getOrientation(int configOrientation) {
        return configOrientation;
    }
}
