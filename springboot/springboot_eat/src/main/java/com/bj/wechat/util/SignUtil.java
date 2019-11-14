package com.bj.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信请求校验工具类
 */
public class SignUtil {
    //与接口配置信息中的Token要一致
    private static String token = "weixin";
    /**
     * 验证签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        //将三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            //将三个参数拼接成一个字符串进行sha1加密
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        //将sha1加密后的字符串可与signature进行对比，标志该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转化为十六进制字符串
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDogest = "";
        for(int i=0;i<byteArray.length;i++){
            strDogest += byteToHexStr(byteArray[i]);
        }
        return strDogest;
    }
    /**
     * 将字节转化为十六进制字符串
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] diget = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tempArr = new char[2];

        tempArr[0] = diget[(mByte >>> 4) & 0X0F];
        tempArr[1] = diget[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
