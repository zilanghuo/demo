package com.zilanghuo.test;

public class Childer extends People{

    static  int age = 12;

    final void finalMethods2(){
        System.out.println("final method-2");
    }

    static void staticGet2() {
        System.out.println("world2");
    }

    {
        System.out.println("childer init");
    }
    Childer(){
        System.out.println("child constrcut");
    }

    static {
        System.out.println("Childer static init");
    }

    public static void main(String[] args) {
        People childer = new Childer();
    }
}
