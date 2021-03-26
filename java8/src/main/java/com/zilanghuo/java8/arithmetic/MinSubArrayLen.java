package com.zilanghuo.java8.arithmetic;

/**
 * Created by laiwufa on 2021-03-12
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,4,5,6,7,8};
        System.out.println(minSubArrayLen(7,nums));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
