package com.zilanghuo.java8.arithmetic;

import cn.hutool.json.JSONUtil;

/**
 * 算法
 */
public class arithmeticDemo {
    public static void main(String[] args) {
        resortArray();
    }

    /**
     * 题目：
     * 1、有一个数值型数组，将奇数放入左边，偶数放入右边，并且最近的偶数是哪一个
     * 2、空间复杂度：1，时间复杂度O(n)
     */
    static void resortArray() {
        int[] arr = new int[]{23, 54, 4, 3, 76, 45, 34, 87, 77, 4};
        int[] newArr = new int[10];
        int jIndex = 0;
        int oIndex = arr.length - 1;
        System.out.println("pre:" + JSONUtil.toJsonStr(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                newArr[oIndex] = arr[i];
                oIndex--;
            } else {
                newArr[jIndex] = arr[i];
                jIndex++;
            }
        }
        System.out.println("first even index:" + (oIndex + 1));
        System.out.println("sort:" + JSONUtil.toJsonStr(newArr));
    }

    /**
     * 题目：
     * 一个三位的数值，它与11的商恰巧等于其每位数的平方
     * 550:50，5*5 +5*5+0*0
     */
    static void thirdCountSame() {
        for (int i = 100; i <= 999; i++) {
            int n = i;
            int j = n / 11;
            int mod = n % 11;
            int sum = 0;
            while (n != 0) {
                sum = sum + (n % 10) * (n % 10);
                n = n / 10;
            }
            if (j == sum && mod == 0) {
                System.out.println("value:" + i);
            }
        }
    }
}
