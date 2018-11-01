package com.zilanghuo.java8.design.decorator;

/**
 * @author laiwufa
 * @date 2018/11/1
 * use:
 */
public class TestMain {
    public static void main(String[] args) {
        Shape circle = new Circle();
        System.out.println("Circle with normal border");
        circle.draw();
        System.out.println("------------------------------------");

        Shape redCircle = new RedShapeDecorator(new Circle());
        System.out.println("Circle of red border");
        redCircle.draw();
        System.out.println("------------------------------------");

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Rectangle of red border");
        redRectangle.draw();
    }
}
