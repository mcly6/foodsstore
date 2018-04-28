package com.shuxin.foodsstore.commons.utils;

import com.shuxin.foodsstore.vo.TableVO;
import org.springframework.data.domain.Page;

import java.util.List;

public class TableVOResultUtils {

    public static TableVO success(Page page) {
        TableVO tableVO = new TableVO();
        if (page.getContent().size() > 0) {

            tableVO.setCode(0);
            tableVO.setMsg("数据查询成功!!!");
            tableVO.setCount(Integer.valueOf((int) page.getTotalElements()));

            tableVO.setData(page.getContent());

        }
        return tableVO;
    }

    public static TableVO success(List<?> list) {
        TableVO tableVO = new TableVO();
        tableVO.setCode(0);
        tableVO.setMsg("数据查询成功!!!");
        tableVO.setCount(Integer.valueOf(list.size()));
        tableVO.setData(list);
        return tableVO;

    }

}
