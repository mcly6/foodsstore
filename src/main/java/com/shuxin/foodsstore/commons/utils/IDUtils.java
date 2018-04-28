package com.shuxin.foodsstore.commons.utils;

import java.util.Random;

/**
 *
 * 功能:  UUID 工具类
 * 详细:
 * 类名:  IDUtils
 *
 */

public class IDUtils {

    /**
     * 获取UUID（32位）
     * @return
     */
    public static synchronized  String getUUID() {
        java.util.UUID id = java.util.UUID.randomUUID();
        String idStr = id.toString();
        idStr = idStr.replaceAll("-", "");
        return idStr;
    }

    /**
     * 批量获取UUID
     * @param number 一次获取UUID的个数
     * @return
     */
    public static String[] getMultiUUID(Integer number) {
        String[] uuids = new String[number];
        for(int i=0;i<number;i++) {
            uuids[i] = getUUID();
        }
        return uuids;
    }

    /**
     * @Title: 获取
     * @Param
     * @Return
     */
    public static synchronized String genUniqueKey() {

        Random random = new Random();

        Integer number = random.nextInt(900000)+100000;

        return System.currentTimeMillis() + String.valueOf(number);

    }
    public static void main(String[] args) {
        /*String uuid = IDUtils.getUUID();
        System.out.println("UUID----->"+uuid);*/

   /*    String keyId = genUniqueKey();

        System.out.println(keyId);*/

        String code = getValidateCode(2);
        System.out.println("code===>: "+code);
    }

    /**
     * @Title: 获取验证码
     * @Param int count 位数
     * @Return
     */
    public static synchronized  String getValidateCode(int count) {
        int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < count; i++)
            sb.append(String.valueOf(array[i]));
        return sb.toString();
    }

}
