package com.zilanghuo.java8.arithmetic;

import cn.hutool.json.JSONUtil;

/**
 * @author laiwufa
 * @date 2019/4/28 21:32
 * 冒泡排序算法
 */
public class BubbleSortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 12, 654, 343, 18, 35, 43, 56, 76, 32, 54};
        System.out.println(JSONUtil.toJsonStr(bubbleSort(arr)));
    }

    /**
     * 分成两次循环，
     * 从第一个与第二个比较，如果第一个大于第二个，第一个将第二个替换
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // i = 0 ,4
            for (int j = i; j < arr.length-1; j++) {

                if (arr[i] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

}
