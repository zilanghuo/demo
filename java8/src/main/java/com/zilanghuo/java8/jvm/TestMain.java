package com.zilanghuo.java8.jvm;

/**
 * @author laiwufa
 * @date 2018/10/30
 * use:
 */
public class TestMain {

    public static void main(String[] args) {
        byte[] allocation1, allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[29000*1024];
        allocation2 = new byte[15000*1024];
        allocation3 = new byte[15000*1024];
        allocation4 = new byte[20000*1024];
        allocation5 = new byte[20000*1024];
    }

    private static int simpleMethod() {
        int x = 13;
        int y = 14;
        int z = x + y;
        return z;
    }
}
