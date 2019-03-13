package com.zilanghuo.java8.design.singleton;

/**
 * 静态内部法
 *
 * @author laiwufa
 * @date 2019/3/13 0013 下午 2:04
 */
public class SingletonLazy {

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        return Holder.singletonModel;
    }

    private static class Holder {
        static SingletonLazy singletonModel = new SingletonLazy();
    }

}
