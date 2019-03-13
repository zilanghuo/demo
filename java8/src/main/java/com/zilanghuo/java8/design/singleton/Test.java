package com.zilanghuo.java8.design.singleton;

/**
 * @author laiwufa
 * @date 2019/3/13 0013 下午 2:07
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            SingletonLazy singletonModel = SingletonLazy.getInstance();
            System.out.println(singletonModel.toString());
        }
    }
}
