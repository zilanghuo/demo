package com.zilanghuo.test;

public class People {

    static  int age = 10;

    void get() {
        System.out.println("hello");
    }
    final void finalMethods(){
        System.out.println("final method");
    }

    static void staticGet() {
        System.out.println("world");
    }
    static {
        System.out.println("people static init");
    }

    {
        System.out.println("people init");
    }
    People(){
        System.out.println("super constrcut");
    }

}
