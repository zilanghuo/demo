package com.zilanghuo.java8.di.diDemo;

/**
 * @author laiwufa
 * @date 2019/4/16 0016 上午 11:09
 */
public class CarDi {
    private FrameworkDi framework;
    CarDi(FrameworkDi framework){
        this.framework = framework;
    }
    public void run(){
        System.out.println("----车子跑起来");
    }
}
