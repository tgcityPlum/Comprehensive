package com.tgcity.base.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author TGCity
 */
public class BaseUtils {
    /**
     * 释放imageview中的图片
     *
     * @param imageView 要被释放的imageView
     * @param viewGroup 要被释放的imageView的父容器
     */
    public static void releaseImageViewResource(ImageView imageView, ViewGroup viewGroup) {
        if (imageView == null) {
            return;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//            Bitmap bitmap = bitmapDrawable.getBitmap();
//            if (bitmap != null && !bitmap.isRecycled()) {
// 会导致正在使用中的bitmap也会被清理掉
//                bitmap.recycle();
//            }
//            bitmap = null;
//            bitmapDrawable = null;

            if (viewGroup != null) {
                //将imageView从父容器移除，并将imag置为null
                viewGroup.removeView(imageView);
                imageView.clearColorFilter();
                imageView.setImageDrawable(null);
                imageView.setImageBitmap(null);
                imageView = null;
            }
            drawable.setCallback(null);
        }
    }


}
