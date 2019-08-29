package com.tgcity.network.base;

import com.tgcity.base.constant.BaseConstant;
import com.tgcity.base.utils.SharedPreferencesUtils;

/**
 * @author TGCity
 * 静态常量类
 */

public class NetworkConstant {
    /*********************************************服务器相关*********************************************/
    /**
     * 开关
     */
    public class Switch {
        //是否为调试模式
        private final static boolean IS_DEBUG = BaseConstant.Power.IS_APP_DEBUG;
        //是否解析服务器返回的json
        public final static boolean IS_JSON_FORMAT = IS_DEBUG && true;
        //是否打印网络请求日志
        public final static boolean IS_PRINT_NETWORK_LOG = IS_DEBUG && true;
    }

    /**
     * 服务器域名
     */
    public class Service {
        //默认域名
        public final static String SERVICE_DEFAULT = "https://app.sharetome.com/";
        public final static String SERVICE_PART_5001 = SERVICE_DEFAULT + ":5001/";
        public final static String SERVICE_PART_5100 = SERVICE_DEFAULT + ":5100/";
        public final static String SERVICE_PART_5101 = SERVICE_DEFAULT + ":5101/";
        public static final String SERVICE_WX = "https://api.weixin.qq.com/";
        //图片域名
        public final static String SERVICE_IMAGE_UPLOAD = "http://img3-upload.youzy.cn/";
    }

    /**
     * 服务器初始化标识
     */
    public class ServiceFlag {
        //微信地址
        public static final int SERVER_WX = 1;
        //老版本API地址
        public static final int SERVER_DEFAULT = 2;
        //新版本API地址
        public static final int SERVER_NEW = 3;
        //图片上传地址
        public static final int SERVER_IMAGE_UPLOAD = 4;
        //默认的分享图片下载地址
        public static final int SERVER_DEFAULT_SHARE_IMAGE = 5;
    }

    public final static String SYSTEM_TAG = "系统逻辑跟踪标签";
    //本字段出现的原因是在于区分TZY_URL这个地址
    public final static String API_SERVICE_TZY = "service_tzy";

    /**
     * 缓存有效期 单位秒
     */
    public static long cache_a_second = 1;



    /*********************************************程序内部通讯消息相关*********************************************/
    //跳转xxx
    public static final int ACTION_HOME_ZYDW = -0x0000A;
    /*********************************************SharedPreferences配置相关*********************************************/
    //缓存配置读取相关

    /**
     * Disk Cache Dir name
     */
    public static String UNIQUE_NAME = "comprehensive-cache";

    /**
     * green dao name
     */
    public static String GREEN_DAO_NAME = "comprehensive-db";

    /**
     * 院校数据相关
     */
    public static int Cache_College = 1;
    /**
     * 专业数据相关
     */
    public static int Cache_Major = 1;
    /**
     * 省控线相关
     */
    public static int Cache_PCL = 1;
    /**
     * 其他
     */
    public static int Cache_Other = 1;

    public static SharedPreferencesUtils sharedPreferencesUtils;

}
