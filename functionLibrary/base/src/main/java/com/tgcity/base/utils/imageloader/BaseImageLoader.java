package com.tgcity.base.utils.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

/**
 * @author TGCity
 * ImageLoader基类 方便拓展 以及更换图片框架
 */
public abstract class BaseImageLoader {

    String getPath(String path) {
        if (path == null) {
            path = "";
        }

        if (!path.startsWith("http") && !path.startsWith("file")) {
            path = "file://" + path;
        }
        return path;
    }

    Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public abstract void display(ImageView imageView, String path, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, DisplayDelegate delegate);

    public abstract void display(ImageView imageView, Integer resourceId);

    public abstract void display(ImageView imageView, Bitmap bitmap);

    public abstract void download(Activity activity, String path, DownloadDelegate delegate);

    public abstract void pause(Context activity);

    public abstract void resume(Context activity);

    public abstract void clear(View view, Context context);

    public interface DisplayDelegate {
        void onSuccess(View view, String path);
    }

    public interface DownloadDelegate {
        void onSuccess(String path, Bitmap bitmap);

        void onFailed(String path);
    }
}
