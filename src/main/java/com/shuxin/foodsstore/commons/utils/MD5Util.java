package com.shuxin.foodsstore.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * 类名:  MD5Util
 * 功能:  MD5加密
 * 详细:
 *
 */
public class MD5Util {

    /**
     * 加密
     * @param s
     * @return
     */
    public  static String MD5(String s) {

        if (s==null||s.length()==0){
            return null;
        }
        char hexDigits[] = { 'A', '1', 'B', '3', 'C', '5', 'D', '7', 'E','9', 'F', '0', 'G', '2', 'H', '4' };
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 比较加密是否一致
     * @param str
     * @param md5Str
     * @return
     */
    public static boolean MD5Validate(String str,String md5Str){

        if (md5Str==null||md5Str.length()==0){
            return false;
        }
        if(md5Str.equals(MD5(str))){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * 描述:  获取MD5加密后字符串
     * 名称:  getMD5
     * 参数:  @param str
     * 参数:  @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String newstr = new BigInteger(1, md.digest()).toString(16);
            return StringUtils.upperCase(newstr);
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {
        String str = getMD5("123456");


        System.out.println(str);
    }
}
