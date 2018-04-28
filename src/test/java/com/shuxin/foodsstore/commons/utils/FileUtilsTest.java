package com.shuxin.foodsstore.commons.utils;

import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void upLoadFile() throws Exception {


    }

    @Test
    public void stringTest() {

        String a = "123,456,789,";

        String b = a.substring(0,a.lastIndexOf(","));

        System.out.println(b);
    }


}