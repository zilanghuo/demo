package com.zilanghuo.java8.design.template;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
 * 模板方法：
 * 1、一个方法，定义一个算法的骨架，子类在不改变算法的骨架前可以自己实现其他的某一具体实现
 */
public class TestMain {

    public static void main(String[] args) {
        AbstractAnimalFactory cat = new Cat();
        cat.eat();

    }

}

class Cat extends AbstractAnimalFactory {

    @Override
    public void eatMethod() {
        System.out.println("this is a cat!");
    }
}