package com.zilanghuo.java8.design.decorator;

/**
 * @author laiwufa
 * @date 2018/11/1
 * use: 原始矩形实现类
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
