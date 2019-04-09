package com.zilanghuo.test;

public class Student extends People {

    public static void main(String[] args) {



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
