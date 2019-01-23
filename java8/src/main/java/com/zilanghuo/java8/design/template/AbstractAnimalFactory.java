package com.zilanghuo.java8.design.template;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
 */
public abstract class AbstractAnimalFactory implements AbstractAnimalEat {

    @Override
    public void eatBefore() {
        System.out.println("eat before");
    }

    @Override
    public abstract void eatMethod();

    @Override
    public void eatAfter() {
        System.out.println("eat after");
    }

    public void eat() {
        eatBefore();
        eatMethod();
        eatAfter();
    }


}
