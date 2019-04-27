package com.zilanghuo.java8.design.iterator.dispose;

/**
 * @author laiwufa
 * @date 2019/4/27 22:57
 * 实现打印菜单，只需要实现对应的迭代器就行
 * 迭代器模式：提供一种方法顺序一个集合对象中的各个元素，而又不暴露其内部的实现
 */
public class TestMain {

    public static void main(String[] args) {
        print(new DinnerMenuRealize().createIterator());
        print(new LunchMenuRealize().createIterator());
    }

    public static void print(MenuItaraBle iterator) {
        while (iterator.hashNext()) {
            System.out.println(iterator.next());
        }

    }
}
