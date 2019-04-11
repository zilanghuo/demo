package com.zilanghuo.test;

public class Student extends People {

    public   int age = 20;

    public static void main(String[] args) {
        System.out.println(new Student().age);
        System.out.println();


    }

    void subGet(){
        staticGet();
        super.get();
        subto();
    }

    static void staticMethod(){

    }

    void subto(){
        staticMethod();
        subGet();
    }
}
