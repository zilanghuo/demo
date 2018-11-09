package com.zilanghuo.java8.calculate;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author laiwufa
 * @date 2018/11/8
 * use:位运算
 * int 表示32位
 * 正数：高位为0
 * 负数：高位为1
 * java中负数是以补码形式保存在数据库的
 */
public class CalculateOne {

    public static void main(String[] args) {
        AtomicInteger ctl = new AtomicInteger(5);
        System.out.println( ctl.compareAndSet(4,-1));
        System.out.println(ctl.get());
    }

    @Test
    public void testMove() {
        /**左移
         * 00000011-->00001100
         */
        System.out.println("5的二进制数字：" + Integer.toBinaryString(5));
        System.out.println("左移结果为：" + (5 << 2) + ",二进制为：" + Integer.toBinaryString(5 << 2));
        System.out.println("-----------");
        /** 右移，负数高位补1
         * 00000111-->00000001
         * 1
         * -2
         */
        System.out.println("7的二进制数字：" + Integer.toBinaryString(7));
        System.out.println("右移结果为：" + (7 >> 2) + ",二进制为：" + Integer.toBinaryString(7 >> 2));
        System.out.println("-7的二进制数字：" + Integer.toBinaryString(-7));
        System.out.println("右移结果为：" + (-7 >> 2) + ",二进制为：" + Integer.toBinaryString(-7 >> 2));
        System.out.println("-----------");
        /**
         * 无符号位移，高位都补0
         */
        System.out.println("7的二进制数字：" + Integer.toBinaryString(7));
        System.out.println("无符号右移结果为：" + (7 >>> 2) + ",二进制为：" + Integer.toBinaryString(7 >>> 2));
        System.out.println("-7的二进制数字：" + Integer.toBinaryString(-7));
        System.out.println("无符号右移结果为：" + (-7 >>> 2) + ",二进制为：" + Integer.toBinaryString(-7 >>> 2));
        System.out.println("-----------");

        /**
         * & 与运算（两个一样，为1，否则为0）
         */
        System.out.println("7的二进制数字：" + Integer.toBinaryString(7));
        System.out.println("4的二进制数字：" + Integer.toBinaryString(4));
        System.out.println("与结果为：" + (7 & 4) + ",二进制为：" + Integer.toBinaryString(7 & 4));
        System.out.println("-3的二进制数字：" + Integer.toBinaryString(-3));
        System.out.println("与结果为：" + (7 & -3) + ",二进制为：" + Integer.toBinaryString(7 & -3));
        System.out.println("-----------");

        /**
         * | 或运算（只要其中一个为1，就为1）
         */
        System.out.println("7的二进制数字：" + Integer.toBinaryString(7));
        System.out.println("4的二进制数字：" + Integer.toBinaryString(4));
        System.out.println("或结果为：" + (7 | 4) + ",二进制为：" + Integer.toBinaryString(7 | 4));
        System.out.println("-3的二进制数字：" + Integer.toBinaryString(-3));
        System.out.println("或结果为：" + (7 | -3) + ",二进制为：" + Integer.toBinaryString(7 | -3));
        System.out.println("-----------");

        /**
         * 相反为1，否则为0
         */
        System.out.println("7的二进制数字：" + Integer.toBinaryString(7));
        System.out.println("4的二进制数字：" + Integer.toBinaryString(4));
        System.out.println("异或结果为：" + (7 ^ 4) + ",二进制为：" + Integer.toBinaryString(7 ^ 4));
        System.out.println("-3的二进制数字：" + Integer.toBinaryString(-3));
        System.out.println("异或结果为：" + (7 ^ -3) + ",二进制为：" + Integer.toBinaryString(7 ^ -3));
        System.out.println("-----------");

        /**
         * 位非运算
         */
        System.out.println("7的二进制数字：" + Integer.toBinaryString(7));
        System.out.println("位非结果为：" + (~7) + ",二进制为：" + Integer.toBinaryString(~7));
        System.out.println("-3的二进制数字：" + Integer.toBinaryString(-3));
        System.out.println("位非结果为：" + (~-3) + ",二进制为：" + Integer.toBinaryString(~-3));
        System.out.println("-----------");
        System.out.println(Integer.toBinaryString(-1));

    }
}
