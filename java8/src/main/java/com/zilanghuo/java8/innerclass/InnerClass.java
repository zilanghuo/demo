package com.zilanghuo.java8.innerclass;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 下午 4:33
 * 局部内部类:定义在方法中
 */
public class InnerClass {
    private static int a;
    private int b;
    public void test(final int c) {
        final int d;
        class Inner {
            public void print() {
                System.out.println(c);
            }
        }
    }
}
