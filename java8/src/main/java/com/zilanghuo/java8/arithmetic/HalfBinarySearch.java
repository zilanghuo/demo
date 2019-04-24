package com.zilanghuo.java8.arithmetic;

/**
 * @author laiwufa
 * @date 2019/4/24 22:28
 */
public class HalfBinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 14, 24, 46, 78, 88, 99};
        int search = 78;
        System.out.println(halfSearch(arr, search));

    }

    /**
     * 二分法搜索
     *
     * @param arr
     * @param a
     */
    public static int halfSearch(int[] arr, int a) {
        int low = 0;
        int hight = arr.length - 1;
        int mid;
        while (low <= hight) {
            mid = (low + hight) / 2;
            if (arr[mid] == a) {
                return mid + 1;
            }
            if (arr[mid] > a) {
                hight = mid - 1;
                System.out.println("arr" + mid + ">a");
            }
            if (arr[mid] < a) {
                low = mid + 1;
                System.out.println("arr" + mid + "<a");
            }

        }
        return -1;
    }

}
