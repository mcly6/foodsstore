package com.shuxin.foodsstore.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Page
 * @Description: 分页辅助类.
 * @Author liu yang  
 * @Date 2018/1/11 11:25
 */ 
@Data
public class Page<E> implements Serializable {

    /**
     * 当前页 .
     */
    private Integer pageNo;

    /**
     * 每页显示数 .
     */
    private Integer pageSize;

    /**
     * 总记录数 .
     */
    private Integer totalRows;

    /**
     * 总页数 .
     */
    private Integer totalPage;

    private List<E> rows;


    public Page() {
    }

    //
    public Page(int pageSize, int pageNo, List<E> rows) {

        this.totalRows = rows.size();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalPage = this.totalRows / this.pageSize;

    }


}
