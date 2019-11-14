package com.bj.wechatserver.util;

import com.bj.wechatserver.enums.BaseEnum;

public class EnumUtil {
    public static <T extends BaseEnum> T getByCode(String code, Class<T> enumClass) {
        //通过反射取出Enum所有常量的属性值
        for (T each: enumClass.getEnumConstants()) {
            //利用code进行循环比较，获取对应的枚举
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
