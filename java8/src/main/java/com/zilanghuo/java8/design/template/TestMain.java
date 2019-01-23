package com.zilanghuo.java8.design.template;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
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