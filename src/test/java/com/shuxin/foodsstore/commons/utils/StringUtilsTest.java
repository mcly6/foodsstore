package com.shuxin.foodsstore.commons.utils;

import org.junit.Test;

import java.util.List;

public class StringUtilsTest {


    @Test
    public void toList() throws Exception {
        //String a = "123,456,789";

        String b = "2017,济南的,第二场雪,化的,同样的,快";
        List<String> list = StringUtils.toList(b);

        for (String s : list) {
            System.out.println(s);

        }

        System.out.println(list.toString());
    }

}