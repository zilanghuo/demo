package com.zilanghuo.java8.reference;

/**
 * 2018/11/18
 *
 * @author laiwufa
 * 基本数据类型，值传递，方法外的值依然没有变更，说明外部的值是没有变更的。
 */
public class ReferenceTest {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        swap(a, b);
        System.out.println("a:" + a);
        System.out.println("b:" + b);

        // 引入部分
        int[] array = new int[]{1, 2, 3, 4};
        System.out.println("pre:" + array[0]);
        swapRefer(array);
        System.out.println("after:" + array[0]);
    }

    /**
     * 复制副本
     *
     * @param num1
     * @param num2
     */
    static void swap(int num1, int num2) {
        int temp = num1;
        num1 = num2;
        num2 = temp;
        System.out.println("num1:" + num1);
        System.out.println("num1:" + num2);
    }

    /**
     * 会修改引用内部的值，浅拷贝，指向的还是同一个引用地址
     *
     * @param a
     */
    static void swapRefer(int[] a) {
        a[0] = 2;
    }

}
