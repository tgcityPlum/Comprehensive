package com.tgcity.base.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;

/**
 * @author TGCity
 * 意图相关工具类
 */
public final class IntentUtils {

    private IntentUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * 判断intent和它的bundle是否为空
     */
    public static boolean isBundleEmpty(Intent intent) {
        return (intent == null) && (intent.getExtras() == null);
    }

    /**
     * 获取卸载App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开App的意图
     *
     * @param packageName 包名
     * @return intent Intent
     */
    public static Intent getLaunchAppIntent(Context context, String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取App具体设置的意图
     *
     * @param packageName 包名
     * @return intent Intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享文本的意图
     *
     * @param content 分享文本
     * @return intent Intent
     */
    public static Intent getShareTextIntent(String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    /**
     * 获取分享图片的意图
     *
     * @param content 分享文本
     * @param uri     图片uri
     * @return intent Intent
     */
    public static Intent getShareImageIntent(String content, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @return intent Intent
     */
    public static Intent getComponentIntent(String packageName, String className) {
        return getComponentIntent(packageName, className, null);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     * @return intent Intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取关机的意图
     * <p>需添加权限 {@code <uses-getPermissionsResult android:name="android.getPermissionsResult.SHUTDOWN"/>}</p>
     *
     * @return intent Intent
     */
    public static Intent getShutdownIntent() {
        Intent intent = new Intent(Intent.ACTION_SHUTDOWN);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取直接拨号意图
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getPhoneIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取跳至拨号界面意图
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getDialIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取拨打电话意图
     * <p>需添加权限 {@code <uses-getPermissionsResult android:name="android.getPermissionsResult.CALL_PHONE"/>}</p>
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getCallIntent(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取跳至发送短信界面的意图
     *
     * @param phoneNumber 接收号码
     * @param content     短信内容
     */
    public static Intent getSendSmsIntent(String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    /**
     * 获取拍照的意图
     *
     * @param outUri 输出的uri
     * @return 拍照的意图
     */
    public static Intent getCaptureIntent(Uri outUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        return intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    /**
     * 获取选择照片的Intent
     *
     * @return Intent
     */
    public static Intent getPickIntentWithGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        return intent.setType("image*//*");
    }

    /**
     * 获取从文件中选择照片的Intent
     *
     * @return Intent
     */
    public static Intent getPickIntentWithDocuments() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        return intent.setType("image*//*");
    }


    public static Intent buildImageGetIntent(Uri saveTo, int outputX, int outputY, boolean returnData) {
        return buildImageGetIntent(saveTo, 1, 1, outputX, outputY, returnData);
    }

    public static Intent buildImageGetIntent(Uri saveTo, int aspectX, int aspectY,
                                             int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image*//*");
        intent.putExtra("output", saveTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    /**
     * 获取裁剪意图
     *
     * @param uriFrom
     * @param uriTo
     * @param outputX
     * @param outputY
     * @param returnData
     * @return
     */
    public static Intent getImageCropIntent(Uri uriFrom, Uri uriTo, int outputX, int outputY, boolean returnData) {
        return buildImageCropIntent(uriFrom, uriTo, 1, 1, outputX, outputY, returnData);
    }

    /**
     * 建立裁剪意图
     *
     * @param uriFrom
     * @param uriTo
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     * @param returnData
     * @return
     */
    public static Intent buildImageCropIntent(Uri uriFrom, Uri uriTo, int aspectX, int aspectY, int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uriFrom, "image*//*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", uriTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    /**
     * 获取屏幕录像意图
     *
     * @param uri
     * @return
     */
    public static Intent getImageCaptureIntent(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    /**
     * 获取系统设置意图
     *
     * @return
     */
    public static Intent getSettingIntent() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        return intent;
    }

    /**
     * 获取时间设置意图
     *
     * @return Intent
     */
    public static Intent getDateIntent() {
        Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
        return intent;
    }

    /**
     * 获取桌面意图
     *
     * @return Intent
     */
    public static Intent getHomeDesktopIntent() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_HOME);
        return intent;
    }

    /**
     * 获取网络意图
     *
     * @return Intent
     */
    public static Intent getNetworkIntent() {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS, null);
        return intent;
    }

    /**
     * 获取GPS意图
     *
     * @return Intent
     */
    public static Intent getGPSIntent() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS, null);
        return intent;
    }

    /**
     * 获取跳转到APP详情页意图(储存,流量,权限,通知等)
     *
     * @param context Context
     */
    public static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }


    /**
     * 获取跳转到浏览器意图
     *
     * @param url
     * @return
     */
    public static Intent getBrowserIntent(String url) {
        return new Intent().setAction("android.intent.action.VIEW").setData(Uri.parse(url));
    }

}
