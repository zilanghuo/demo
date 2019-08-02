package com.zilanghuo.java8;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author laiwufa
 * @date 2019/6/23 22:21
 */
public class LambdaDemo {

    public static void main(String[] args) {

        String[] arr = {"xiao", "nihao", "dfajdf"};
        // Arrays.sort(arr, (name1, name2) -> name1.length() - name2.length());
        Arrays.sort(arr, Comparator.comparingInt(String::length));
      //  Arrays.sort(arr, StringOrder::byLength);
        System.out.println(Arrays.toString(arr));
    }
}
