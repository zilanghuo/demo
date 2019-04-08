package com.zilanghuo.java8.jvm;

/**
 * 输出class load信息
 * -XX:+TraceClassLoading
 */
public class TraceClassLoadDemo extends Father{

    static {
        System.out.println("subClass init;");
    }

    public static void main(String[] args) {
        System.out.println(TraceClassLoadDemo.age);
    }

}

class Father{

    public static int age = 10;

    static {
        System.out.println("father class init.");
    }

}