package com.zilanghuo.java8.design.decorator;

/**
 * @author laiwufa
 * @date 2018/11/1
 * use: 红色的装饰实现
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }
    @Override
    public void draw() {
        super.draw();
        // 装饰器的业务代码
        System.out.println("RedShapeDecorator 红色装饰器的业务代码");
    }
}
