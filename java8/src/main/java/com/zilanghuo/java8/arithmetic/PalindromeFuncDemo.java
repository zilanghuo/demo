package com.zilanghuo.java8.arithmetic;

/**
 * @author laiwufa
 * @date 2019/5/9 0009 下午 6:00
 * 回文函数的计算
 */
public class PalindromeFuncDemo {

    public static void main(String[] args) {
        String str = "abba";
        Boolean palindromeStr = isPalindromeStr(str.toCharArray(), 0, str.length() - 1);
        System.out.println(palindromeStr);
    }

    /**
     * 判断是否未回文字符串
     *
     * @param chars
     * @param first
     * @param last
     * @return
     */
    static Boolean isPalindromeStr(char[] chars, int first, int last) {
        int mid = chars.length / 2;
        if (chars.length % 2 != 0) {
            mid = mid + 1;
        }
        if (last >= mid) {
            if (chars[first] == chars[last]) {
                System.out.println("------palindrome");
                return isPalindromeStr(chars, ++first, --last);
            }
        }
        System.out.println("------not palindrome");
        return false;
    }

}
