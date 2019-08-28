package com.tgcity.network.utils;

import android.app.Application;

/**
 * @author TGCity
 * 该类用于调用so文件方法
 * 后续更多方法和字段添加与我联系
 * 看红色不爽的（实际并不影响编译）
 * 选择File->Settings->Inspections->搜索框搜索native->找到Missing JNI function,把它右边的勾勾去掉->Apply
 */
@SuppressWarnings("ALL")
public class NativeUtil {

    static {
        System.loadLibrary("youzy-lib");
    }

    /**
     * 获取ca证书
     */
    public static native String getCaCertificate();

    /**
     * 获取签名
     */
    public static native String youzySign();

    public static native String youzyApiSign();

    public static native String youzyDATASign();

    public static native String youzySuperSign();

    public static native String youzySignMedia();

    /**
     * 授权
     */
    public static native boolean soSignatures(Application context);
}
