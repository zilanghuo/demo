package com.zilanghuo.test;

import java.util.Calendar;

/**
 * @author laiwufa
 * @date 2019/3/6 0006 上午 9:31
 */
public class DateUtils {

    public static void main(String[] args) {

        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1551628800009L);
        System.out.println(instance.getTime());

        instance.setTimeInMillis(1551715199009L);
        System.out.println(instance.getTime());

        instance.setTimeInMillis(1236244031746L);
        System.out.println(instance.getTime());

    }

}
