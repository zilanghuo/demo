package com.zilanghuo.java8.design.flyweight;

/** 享元模式
 * @author laiwufa
 * @date 2019/8/2 9:07
 */
public class TestMain {

    public static void main(String[] args) {
        BigString bigString = new BigString("129875024895");
        bigString.print();
    }
}
