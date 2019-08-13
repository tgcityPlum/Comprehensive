package com.tgcity.web;

/**
 * WebView错误集合
 */

public class WebViewErrorCodeConstant {
    /**
     * 未知错误
     */
    public static final int ERROR_UNKNOWN = -1;
    /**
     * 服务器或代理主机名查找失败
     */
    public static final int ERROR_HOST_LOOKUP = -2;
    /**
     * 不支持的身份验证方案（不是基本的或摘要）
     */
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    /**
     * 服务器上的用户身份验证失败
     */
    public static final int ERROR_AUTHENTICATION = -4;
    /**
     * 代理上的用户身份验证失败
     */
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    /**
     * 未能连接到服务器
     */
    public static final int ERROR_CONNECT = -6;
    /**
     * 无法读取或写入服务器
     */
    public static final int ERROR_IO = -7;
    /**
     * 连接超时
     */
    public static final int ERROR_TIMEOUT = -8;
    /**
     * 太多重定向
     */
    public static final int ERROR_REDIRECT_LOOP = -9;
    /**
     * 不支持URI方案
     */
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;
    /**
     * 执行SSL握手失败
     */
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    /**
     * 网址错误
     */
    public static final int ERROR_BAD_URL = -12;
    /**
     * 通用文件错误
     */
    public static final int ERROR_FILE = -13;
    /**
     * 找不到文件
     */
    public static final int ERROR_FILE_NOT_FOUND = -14;
    /**
     * 同一期间请求太多
     */
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
}
