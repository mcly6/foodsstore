package com.shuxin.foodsstore.commons.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {


    /**   
     * @Title: 将","分隔的字符串转换为数组
     * @Param   
     * @Return   
     */ 
    public static List<String> toList(String arrString) {

        String[] strArr = arrString.split(",");

        List<String> strList = new ArrayList<>();

        for (String s : strArr) {

            strList.add(s);

        }
        return strList;


    }

}
