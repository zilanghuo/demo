package com.zilanghuo;

/**
 * Created by laiwufa on 2020-08-19
 */
public class TestMain {

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {
            test(i);
        }

    }


    private static void test(int i){
        if (i == 5){
            return;
        }
        System.out.println(i);
    }

}
