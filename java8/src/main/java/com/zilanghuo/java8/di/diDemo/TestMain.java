package com.zilanghuo.java8.di.diDemo;

/**
 * @author laiwufa
 * @date 2019/4/16 0016 上午 11:10
 * 依赖导致
 */
public class TestMain {

    public static void main(String[] args) {
        TireDi tireDi = new TireDi();
        BottomDi bottomDi = new BottomDi(tireDi);
        FrameworkDi frameworkDi = new FrameworkDi(bottomDi);
        CarDi carDi = new CarDi(frameworkDi);
        carDi.run();

    }
}
