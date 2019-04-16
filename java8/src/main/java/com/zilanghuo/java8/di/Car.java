package com.zilanghuo.java8.di;

/**
 * @author laiwufa
 * @date 2019/4/16 0016 上午 11:09
 */
public class Car {
    private Framework framework;
    Car(){
        this.framework = new Framework();
    }
    public void run(){
        System.out.println("----车子跑起来");
    }
}
