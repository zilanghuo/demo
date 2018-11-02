package com.zilanghuo.spi;

public class Student implements People {

    @Override
    public void sayHello() {
        System.out.println("hello,student");
    }
}
