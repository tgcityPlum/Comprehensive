package com.tgcity.refreshview.springview.listener;

import android.support.design.widget.AppBarLayout;

/**
 * @author TGCity
 */

public abstract class AbstractAppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        /**
         *  EXPANDED  COLLAPSED  IDLE
         */
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    /**
     * onStateChanged
     * @param appBarLayout  AppBarLayout
     * @param state State
     */
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}

