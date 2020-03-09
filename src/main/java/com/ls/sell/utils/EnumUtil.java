package com.ls.sell.utils;

import com.ls.sell.enums.CodeEnums;

public class EnumUtil {

    public static <T extends CodeEnums> T getByCode(Integer code ,Class<T> enumClass){
        for (T element : enumClass.getEnumConstants()) {
            if(code.equals(element.getCode())){
                return element;
            }
        }
        return null;
    }
}
