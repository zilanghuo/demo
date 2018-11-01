package com.zilanghuo.java8.design.decorator;

/**
 * @author laiwufa
 * @date 2018/11/1
 * use:原始圆形实现类
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
