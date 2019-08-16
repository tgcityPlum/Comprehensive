package com.tgcity.base.utils.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;


/**
 * @Describe glide 加载程序
 *
 * 请根据实际需要拓展
 * 初版 仅仅实现简单展示
 */
public class GlideImageLoader extends ImageLoader{

    @Override
    public void display(final ImageView imageView, String path, int loadingResId, int failResId, int width, int height, final DisplayDelegate delegate) {
        final String finalPath = getPath(path);
        Activity activity = getActivity(imageView);

        RequestOptions requestOptions=new RequestOptions();

        //图片质量
        requestOptions.format(DecodeFormat.PREFER_RGB_565);

        //占位符
        if(loadingResId!=0){
            requestOptions.placeholder(loadingResId);
        }
        //异常占位符
        if(failResId!=0){
            requestOptions.error(loadingResId);
        }
        //宽高 特殊使用
        if(width!=0||height!=0){
            requestOptions.override(width, height);
        }
        Glide.with(activity)
                .load(finalPath)
                .apply(requestOptions).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (delegate != null) {
                    delegate.onSuccess(imageView, finalPath);
                }
                return false;
            }
        }).into(imageView);
    }

    @Override
    public void display(ImageView imageView, Integer resourceId) {
        Activity activity = getActivity(imageView);
        Glide.with(activity).load(resourceId).into(imageView);
    }

    @Override
    public void display(ImageView imageView, Bitmap bitmap) {
        Activity activity = getActivity(imageView);
        Glide.with(activity).load(bitmap).into(imageView);
    }

    @Override
    public void download(Activity activity,String path, final DownloadDelegate delegate) {
        final String finalPath = getPath(path);
        Glide.with(activity).asBitmap().load(finalPath).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (delegate != null) {
                    delegate.onSuccess(finalPath, resource);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (delegate != null) {
                    delegate.onFailed(finalPath);
                }
            }
        });
    }

    @Override
    public void pause(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void resume(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 清理View中的图片
     * @param view    要被清理的View
     * @param context
     */
    @Override
    public void clear(View view, Context context) {
        Glide.with(context).clear(view);
    }
}
