package com.zilanghuo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laiwufa
 * @date 2019/6/23 16:03
 */
public class SteamDemo {

    public static void main(String[] args) {
        listJavaMethod();

    }

    private static void listJavaMethod() {
        List<String> listOne = new ArrayList();
        listOne.add("a");
        listOne.add("b");
        listOne.add("c");
        listOne.add("d");
        // 遍历
        listOne.stream().forEach(str -> System.out.print(str));
        System.out.println("--------------------------------");
        // 对数据进行处理
        listOne = listOne.stream().map(str -> "--"+str).collect(Collectors.toList());
        listOne.stream().forEach(str -> System.out.print(str));
        System.out.println("--------------------------------");
        // 对数据进行过滤
        listOne = listOne.stream().filter(s -> !s.equals("--b")).collect(Collectors.toList());
        listOne.stream().forEach(str -> System.out.print(str));
    }
}
