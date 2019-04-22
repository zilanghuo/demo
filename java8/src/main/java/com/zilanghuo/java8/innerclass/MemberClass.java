package com.zilanghuo.java8.innerclass;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 下午 4:32
 * 成员内部类
 */
public class MemberClass {
    private static int a;
    private int b;
    public class Inner {
        public void print() {
            System.out.println(a);
            System.out.println(b);
        }
    }
}
