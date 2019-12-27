package com.zilanghuo.java8;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * @author laiwufa
 * @date 2019/6/23 22:21
 */
public class LambdaDemo {

    public static void main(String[] args) {

        int[] array = {23,43,56,97,32};
        Arrays.stream(array).reduce((x,y) -> x+y).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).reduce(Integer::sum).ifPresent(s -> System.out.println(s));
       // Arrays.stream(array).reduce(StatisticsUtility::addIntData).ifPresent(s -> System.out.println(s));

        long start = 1573286400000L;


        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(start);
        Date date = instance.getTime();

        // 1573286400000
        // 1574233131530
        String str = "34324,432,4324";
        System.out.println(str.substring(0,str.indexOf(",")));

    }
}
