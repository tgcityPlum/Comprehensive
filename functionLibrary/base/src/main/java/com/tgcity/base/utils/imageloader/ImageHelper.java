package com.tgcity.base.utils.imageloader;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


/**
 * @author TGCity
 * Image辅助类
 */
public class ImageHelper {
    private static final String TAG = ImageHelper.class.getSimpleName();
    private static BaseImageLoader sImageLoader;

    private static BaseImageLoader getImageLoader() {
        if (sImageLoader == null) {
            synchronized (ImageHelper.class) {
                if (sImageLoader == null) {
                    if (isClassExists("com.bumptech.glide.Glide")) {
                        sImageLoader = new GlideImageLoader();
                    } else {
                        throw new RuntimeException("必须在你的build.gradle文件中配置「Glide、Picasso、universal-image-loader、XUtils3」中的某一个图片加载库的依赖");
                    }
                }
            }
        }
        return sImageLoader;
    }

    /**
     * 设置开发者自定义 BaseImageLoader
     *
     * @param imageLoader BaseImageLoader
     */
    public static void setImageLoader(BaseImageLoader imageLoader) {
        sImageLoader = imageLoader;
    }

    /**
     * 检查类是否存在
     * @param classFullName classFullName
     */
    private static boolean isClassExists(String classFullName) {
        try {
            Class.forName(classFullName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 加载网络图片
     *
     * @param imageView  ImageView
     * @param path  String
     */
    public static void display(ImageView imageView, String path) {
        display(imageView, 0, path);
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, String path) {
        display(imageView, placeholderResId, path, 0);
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, String path, int size) {
        display(imageView, placeholderResId, path, size, size);
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, String path, int width, int height) {
        display(imageView, placeholderResId, path, width, height, null);
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, String path, int width, int height, final BaseImageLoader.DisplayDelegate delegate) {
        display(imageView, placeholderResId, placeholderResId, path, width, height, delegate);
    }

    public static void display(ImageView imageView, @DrawableRes int loadingResId, @DrawableRes int failResId, String path, int width, int height, final BaseImageLoader.DisplayDelegate delegate) {
        getImageLoader().display(imageView, path, loadingResId, failResId, width, height, delegate);
    }


    /**
     * 加载本地资源
     *
     * @param imageView  ImageView
     * @param resourceId  Integer
     */
    public static void display(ImageView imageView, Integer resourceId) {
        getImageLoader().display(imageView, resourceId);
    }

    public static void display(ImageView imageView, Bitmap bitmap) {
        getImageLoader().display(imageView, bitmap);
    }

    /**
     * 下载图片
     */
    public static void download(Activity activity, String path, final BaseImageLoader.DownloadDelegate delegate) {
        try {
            getImageLoader().download(activity, path, delegate);
        } catch (Exception e) {
            Log.d(TAG, "下载图片失败：" + e.getMessage());
        }
    }

    /**
     * 暂停加载
     *
     * @param context  Context
     */
    public static void pause(Context context) {
        getImageLoader().pause(context);
    }

    /**
     * 继续加载
     *
     * @param context  Context
     */
    public static void resume(Context context) {
        getImageLoader().resume(context);
    }

    /**
     * 清理View中的图片
     *
     * @param view    要被清理的View
     * @param context  Context
     */
    public static void clear(View view, Context context) {
        getImageLoader().clear(view, context);
    }

}
