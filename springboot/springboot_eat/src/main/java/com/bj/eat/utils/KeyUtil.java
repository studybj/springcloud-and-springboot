package com.bj.eat.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成主键，格式为时间+随机数
     */
    public static synchronized String  genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
