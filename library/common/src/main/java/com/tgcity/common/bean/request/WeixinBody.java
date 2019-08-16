package com.tgcity.common.bean.request;

/**
 * Created by Administrator on 2018/9/11.
 */

public class WeixinBody {
    private String path;
    private int width;
    private boolean auto_color;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isAuto_color() {
        return auto_color;
    }

    public void setAuto_color(boolean auto_color) {
        this.auto_color = auto_color;
    }
}
