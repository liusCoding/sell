package com.ls.sell.vo;
import lombok.Data;


/**
 * @program: sell->ResultVO
 * @description:
 * @author: liushuai
 * @create: 2019-08-04 12:07
 **/

@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String  msg;

    /** 具体内容 */
    private T data;
}
