package com.zilanghuo.java8.arithmetic;

/**
 * 算法
 */
public class arithmeticDemo {
    public static void main(String[] args) {
        thirdCountSame();
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
