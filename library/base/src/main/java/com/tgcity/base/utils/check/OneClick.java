package com.tgcity.base.utils.check;

import java.util.Calendar;

public class OneClick {
    private String methodName;
    private static final int CLICK_DELAY_TIME = 1000;
    private static final int CLICK_DELAY_TIME1 = 400;
    private long lastClickTime = 0;

    public OneClick(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean check() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }
    public boolean check400() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > CLICK_DELAY_TIME1) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }
}