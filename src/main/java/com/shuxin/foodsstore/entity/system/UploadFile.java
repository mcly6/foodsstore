package com.shuxin.foodsstore.entity.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName: UploadFile
 * @Description: 上传文件表.   
 * @Author liu yang  
 * @Date 2018/1/9 16:08  
 */
@Data
@Entity
public class UploadFile implements Serializable {

    @Id
    private String id ;


    /** 上传业务 .*/
    private String  fileModule ;

    /** 路径 .*/
    private String filePath ;

    /** 文件类型 .*/
    private String fileType ;

}
