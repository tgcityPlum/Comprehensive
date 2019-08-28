package com.tgcity.refreshadapter.animation;

import android.animation.Animator;
import android.view.View;

/**
 * @author TGCity
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public interface BaseAnimation {

    /**
     * getAnimators
     * @param view View
     * @return Animator
     */
    Animator[] getAnimators(View view);
}
