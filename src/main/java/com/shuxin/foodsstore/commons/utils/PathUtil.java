package com.shuxin.foodsstore.commons.utils;

import java.io.File;

/**
 * @ClassName: PathUtil
 * @Description: 资源路径辅助类.
 * @Author liu yang  
 * @Date 2018/1/9 15:30  
 */ 
public class PathUtil {
    public static String getPath(String path) {
        //String classPath = Tools.class.getClassLoader().getResource("/").getPath();
        //String rootPath  = "";

        //windows下
        if("\\".equals(File.separator)){
            //rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
            path = path.replace("/", "\\");
        }

        //linux下
        if("/".equals(File.separator)){
            //rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
            path = path.replace("\\", "/");
        }
        return path;
    }
}
