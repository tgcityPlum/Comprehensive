package com.tgcity.network.utils;

import com.tgcity.base.utils.LogUtils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MD5Util {

    /**
     * @param message
     * @return
     * @Description: 生成MD5
     * @author: tianpengw
     */
    public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // 创建一个md5算法对象
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte); // 获得MD5字节数组,16*8=128位
            md5 = bytesToHex(md5Byte); // 转换为16进制字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * @param bytes
     * @return
     * @Description: 二进制转十六进制
     * @author: tianpengw
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }

    /**
     * @param params 参数内容
     * @param key    key值
     * @return
     * @Description: 签名：请求参数排序并后面补充key值，最后进行MD5加密，返回大写结果
     * @author: tianpengw
     */
    public static String signatures(Map<String, Object> params, String key) {
        String signatures = "";
        try {
            List<String> paramsStr = new ArrayList<String>();
            for (String key1 : params.keySet()) {
                if (null != key1 && !"".equals(key1)) {
                    paramsStr.add(key1);
                }
            }
//            Collections.sort(paramsStr);
            StringBuilder sbff = new StringBuilder();
            for (String kk : paramsStr) {
                String value = params.get(kk).toString();
                if ("".equals(sbff.toString())) {
                    sbff.append(kk + "=" + value);
                } else {
                    sbff.append("&" + kk + "=" + value);
                }
            }
            //加上key值
            sbff.append("&" + key);
            LogUtils.i("NetworkResponseEntity           "+sbff.toString().toLowerCase());
            signatures = getMD5(sbff.toString().toLowerCase()).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signatures;
    }
}