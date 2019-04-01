package com.zilanghuo.test;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(test());
    }

    static int test() {
        int i = 0 ;
        try {
            return i;
        } catch (Exception e) {
            return -1;
        } finally {
            i++;
        }
    }

    void getOne(){
        return;
    }

    static void a(){
    }

}
