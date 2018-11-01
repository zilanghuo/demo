package com.zilanghuo.java8.design.decorator;

/**
 * @author laiwufa
 * @date 2018/11/1
 * use: shape 装饰接口
 */
public abstract class ShapeDecorator implements Shape {
    private Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }
    @Override
    public void draw(){
        decoratedShape.draw();
    }
}
