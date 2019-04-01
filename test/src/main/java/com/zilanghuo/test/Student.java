package com.zilanghuo.test;

public class Student extends People {

    void subGet(){
        staticGet();
        super.get();
        subto();
    }

    void subto(){
        subGet();
    }
}
