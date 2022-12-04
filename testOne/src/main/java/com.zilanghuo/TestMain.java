package com.zilanghuo;

/**
 * Created by laiwufa on 2020-08-19
 */
public class TestMain {

    public static void main(String[] args) throws Exception {

        String str = "720m";


        System.out.println(str.substring(0,str.length() -1));

    }


    private static void test(int i){
        if (i == 5){
            return;
        }
        System.out.println(i);
    }

}
