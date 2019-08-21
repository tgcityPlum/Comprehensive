package com.tgcity.network.utils;

import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * xxx全局常用方法汇总
 * 分为 数据绑定方法区、页面跳转方法区、业务流程方法区、垃圾清理方法区、控件状态更改区、状态判断方法区、杂项专区
 * Created by Administrator on 2018/9/13.
 */

@SuppressWarnings("ALL")
public class CommonUtils {

    /**
     * 判断返回的数据是否为空
     *
     * @param listHttpResult 解析完的服务器数据
     * @param <T>
     * @return
     */
    public static <T> boolean isEmptyResult(List<T> listHttpResult) {
        try {
            if (listHttpResult == null) {
                return true;
            }

            if (listHttpResult.size() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 获取设备IP地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    //加上这个地址获取的一定是IPV4地址  不加的话 有可能是IPV6地址
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("VOLLEY", ex.toString());
        }
        return "127.0.0.1(error)";
    }

}
