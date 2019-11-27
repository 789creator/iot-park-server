
package com.szzt.iot.common.utils;


import cn.hutool.core.util.StrUtil;
import com.szzt.iot.common.netty.proto.IMBaseDefine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 */
public class CommonUtils {

    /**
     * Convert a integer value to bytes
     * 
     * @param n the integer value to convert
     * @return bytes of then integer value
     * @since  1.0
     */
    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
        }
        return b;
    }
    /**
     * Convert a float value to bytes
     * 
     * @param f the float value to convert
     * @return bytes of then float value
     * @since  1.0
     */
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }

    /**
     * 将byte数组转换为int数据
     * 
     * @param b 字节数组
     * @return 生成的int数据
     */
    public static int byteArray2int(byte[] b) {
        return (((int) b[0]) << 24) + (((int) b[1]) << 16) + (((int) b[2]) << 8) + b[3];
    }

    /**
     * Checks the text value is an url or not
     * @Description 判断是否是url
     * @param text the text value to check
     * @return true : is an url, false : not
     */
    public static String matchUrl(String text) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        Pattern p = Pattern.compile("(https?:)?//[0-9A-Za-z:/[-]_#[?][=][.][&]]*",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    /**
     * Checks the url is point to a gif image or not
     * @param url the url to check
     * @return true : is gif, false : not
     * @since  1.0
     */
    public static boolean gifCheck(String url) {
        boolean isGif = !StrUtil.isEmpty(url) && url.equals(CommonUtils.matchUrl(url))
                && url.toLowerCase().endsWith(".gif");
        return isGif;
    }

    /**
     * Returns the current time in seconds.
     * @return current time
     * @since  1.0
     */
    public static int currentTimeSeconds() {
        long time = System.currentTimeMillis();
        return (int) (time /1000);
    }
    
    /**
     * 判断是否是PC端
     * @param clientType
     * @return
     * @since  1.0
     */
    public static boolean isPc(int clientType) {
        if (IMBaseDefine.ClientType.CLIENT_TYPE_MAC_VALUE == clientType
                || IMBaseDefine.ClientType.CLIENT_TYPE_WINDOWS_VALUE == clientType) {
            return true;
        }
        return false;
    }
    /**
     * 判断是否是PC端
     * @param clientType
     * @return
     * @since  1.0
     */
    public static boolean isPc(IMBaseDefine.ClientType clientType) {
        if (IMBaseDefine.ClientType.CLIENT_TYPE_MAC == clientType 
                || IMBaseDefine.ClientType.CLIENT_TYPE_WINDOWS == clientType) {
            return true;
        }
        return false;
    }
    /**
     * 判断是否是移动端
     * @param clientType
     * @return
     * @since  1.0
     */
    public static boolean isMobile(int clientType) {
        if (IMBaseDefine.ClientType.CLIENT_TYPE_ANDROID_VALUE == clientType 
                || IMBaseDefine.ClientType.CLIENT_TYPE_IOS_VALUE == clientType) {
            return true;
        }
        return false;
    }
    /**
     * 判断是否是移动端
     * @param clientType
     * @return
     * @since  1.0
     */
    public static boolean isMobile(IMBaseDefine.ClientType clientType) {
        if (IMBaseDefine.ClientType.CLIENT_TYPE_ANDROID == clientType 
                || IMBaseDefine.ClientType.CLIENT_TYPE_IOS == clientType) {
            return true;
        }
        return false;
    }
    /**
     * @param msgType
     * @return
     * @since  1.0
     */
    public static boolean isMessageTypeSinble(IMBaseDefine.MsgType msgType) {
        if (IMBaseDefine.MsgType.MSG_TYPE_SINGLE_AUDIO == msgType
                || IMBaseDefine.MsgType.MSG_TYPE_SINGLE_TEXT == msgType) {
            return true;
        }
        return false;
    }
    /**
     * @param messageType
     * @return
     * @since  1.0
     */
    public static boolean isGroup(IMBaseDefine.MsgType messageType) {
        return IMBaseDefine.MsgType.MSG_TYPE_GROUP_TEXT == messageType || IMBaseDefine.MsgType.MSG_TYPE_GROUP_AUDIO == messageType;
    }
    /**
     * @param messageType
     * @return
     * @since  1.0
     */
    public static boolean isGroup(int messageType) {
        return IMBaseDefine.MsgType.MSG_TYPE_GROUP_TEXT_VALUE == messageType || IMBaseDefine.MsgType.MSG_TYPE_GROUP_AUDIO_VALUE  == messageType;
    }
    /**
     * @param messageType
     * @return
     * @since  1.0
     */
    public static boolean isSingle(IMBaseDefine.MsgType messageType) {
        return IMBaseDefine.MsgType.MSG_TYPE_SINGLE_TEXT == messageType || IMBaseDefine.MsgType.MSG_TYPE_SINGLE_AUDIO == messageType;
    }
    /**
     * @param msgType
     * @return
     * @since  1.0
     */
    public static boolean isAudio(int msgType) {
        return IMBaseDefine.MsgType.MSG_TYPE_SINGLE_AUDIO_VALUE == msgType || IMBaseDefine.MsgType.MSG_TYPE_GROUP_AUDIO_VALUE == msgType;
    }

    /**
     * @param msgType
     * @return
     * @since  1.0
     */
    public static boolean isAudio(IMBaseDefine.MsgType msgType) {
        return IMBaseDefine.MsgType.MSG_TYPE_SINGLE_AUDIO == msgType || IMBaseDefine.MsgType.MSG_TYPE_GROUP_AUDIO == msgType;
    }
    
    /**
     * 由token判断客户端类型
     * 
     * @param userToken 用户token
     * @return
     * @since  3.0
     */
    public static IMBaseDefine.ClientType getClientTypeOfToken(String userToken) {
        if (userToken != null) {
            if (userToken.startsWith("ios:")) {
                return IMBaseDefine.ClientType.CLIENT_TYPE_IOS;
            } else if (userToken.startsWith("android:")) {
                    return IMBaseDefine.ClientType.CLIENT_TYPE_IOS;
            } else {
                return IMBaseDefine.ClientType.CLIENT_TYPE_WINDOWS;
            }
        }
        return IMBaseDefine.ClientType.CLIENT_TYPE_WINDOWS;
    }
}
