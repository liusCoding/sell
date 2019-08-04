package com.ls.sell.utils;

import com.ls.sell.vo.ResultVO;

/**
 * @program: sell->ResultVOUtil
 * @description: 前端返回结果工具类
 * @author: liushuai
 * @create: 2019-08-04 17:25
 **/

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();

        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
