package com.shuxin.foodsstore.commons.utils;

import com.shuxin.foodsstore.vo.ResultVO;


public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
         resultVO.setCode(200);


         resultVO.setMsg("成功");
         resultVO.setData(object);

         return resultVO;

    }

    public  static ResultVO success(){
        return success(null);
    }

    public  static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return  resultVO;
    }
}
