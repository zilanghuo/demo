package com.zilanghuo.java8.innerclass;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 下午 4:30
 * 静态内部类
 */
public class StaticClass {
    private static int a;
    private int b;
    public static class Inner {
        public void print() {
            System.out.println(a);
        }
    }
}
