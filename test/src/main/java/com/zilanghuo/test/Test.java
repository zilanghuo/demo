package com.zilanghuo.test;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    public static void main(String[] args) {
        String a = new String("aa");
        String b = new String("aa");
        System.out.println(a == b);

        String a1 = "cc";
        String b1 = "cc";
        String c1 = new String("cc");
        System.out.println(a1 == b1);
        System.out.println(a1 == c1);

    }

}
