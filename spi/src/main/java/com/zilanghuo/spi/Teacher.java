package com.zilanghuo.spi;

public class Teacher implements People {
    @Override
    public void sayHello() {
        System.out.println("hello,teacher");
    }
}
