package com.zilanghuo.java8.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laiwufa
 * @date 2018/10/30
 * use:extends 和 super之间的区别
 */
public class TestMain {

    public static void main(String[] args) {
        List<Animal> animal = new ArrayList();
        List<Cat> cat = new ArrayList();
        List<CarField> carField = new ArrayList();
        // init
        animal.add(new Animal());
        cat.add(new Cat());
        carField.add(new CarField());
        // extends\super

        // List<? extends Cat> extendsCatForAnimal = animal;
        List<? super Cat> superCatForAnimal = animal;

        List<? extends Cat> extendsCatForCat = cat;
        List<? super Cat> superCatForCat = cat;

        List<? extends Cat> extendsCatForCar = carField;

        // List<? super Cat> superCatForCar = carField;






    }
}
