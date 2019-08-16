package com.tgcity.network.base;

import com.tgcity.network.utils.SPUtils;

/**
 * Created by Administrator on 2018/7/18.
 * 静态常量类
 */

public class NetworkConstant {
    /*********************************************服务器相关*********************************************/
    //开关
    public class Switch {
        private final static boolean isDebug = true;//是否为调试模式
        public final static boolean isJsonFormat = isDebug && true;//是否解析服务器返回的json
        public final static boolean isPrintNetworkLog = isDebug && true;//是否打印网络请求日志
    }

    //服务器域名
    public class Service {
        public final static String SERVICE_DEFAULT = "https://app.sharetome.com/";//默认域名
        public final static String SERVICE_PART_5001 = SERVICE_DEFAULT + ":5001/";
        public final static String SERVICE_PART_5100 = SERVICE_DEFAULT + ":5100/";
        public final static String SERVICE_PART_5101 = SERVICE_DEFAULT + ":5101/";
        public static final String SERVICE_WEIXIN = "https://api.weixin.qq.com/";
        public final static String SERVICE_IMAGE_UPLOAD = "http://img3-upload.youzy.cn/";//图片域名
        public final static long SERVICE_TRY_WAIT = 10 * 1000;//出错重试等待时间（暂时定为10秒）
        public final static long SERVICE_TRY_INTERVAL = 1 * 1000;//出错重试时的间隔时间（暂时定为1秒）
    }

    //服务器初始化标识
    public class ServiceFlag {
        public static final int SERVER_WX = 1;//微信地址
        public static final int SERVER_DEFAULT = 2;//老版本API地址
        public static final int SERVER_NEW = 3;//新版本API地址
        public static final int SERVER_IMAGE_UPLOAD = 4;//图片上传地址
        public static final int SERVER_DEFAULT_SHARE_IMAGE = 5;//默认的分享图片下载地址
    }

    public final static String SYSTEM_TAG = "系统逻辑跟踪标签";
    public final static String API_SERVICE_TZY = "service_tzy";//本字段出现的原因是在于区分TZY_URL这个地址

    //缓存有效期 单位秒
    public static long cache_a_second = 1;

    /*********************************************用来区分item类型*********************************************/
    public class ItemType {
        public static final int LEVEL_0 = 0;
        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;
    }

    /*********************************************程序内部通讯消息相关*********************************************/
    public static final int ACTION_HOME_ZYDW = -0x0000A;//跳转xxx
    /*********************************************SharedPreferences配置相关*********************************************/
    //缓存配置读取相关
    public static int Cache_College = 1;//院校数据相关
    public static int Cache_Major = 1;//专业数据相关
    public static int Cache_PCL = 1;//省控线相关
    public static int Cache_Other = 1;//其他

    public static SPUtils spUtils;

    public class SP {
        public static final String CONFIG = "youzy_cfg";//配置文件名
        public static final String CACHE_COLLEGE = "Cache_College";//缓存院校
        public static final String CACHE_MAJOR = "Cache_Major";//缓存专业
        public static final String CACHE_PCL = "Cache_PCL";//缓存省控线
        public static final String CACHE_OTHER = "Cache_Other";//缓存其他
    }

}
