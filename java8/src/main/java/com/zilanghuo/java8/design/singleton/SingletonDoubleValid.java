package com.zilanghuo.java8.design.singleton;

/**
 * 双重检查锁
 *
 * @author laiwufa
 * @date 2019/3/13 0013 下午 2:18
 */
public class SingletonDoubleValid {
    private SingletonDoubleValid() {
    }

    private static volatile SingletonDoubleValid singletonDoubleValid = null;

    public static SingletonDoubleValid getInstance() {
        if (singletonDoubleValid == null) {
            synchronized (SingletonDoubleValid.class) {
                if (singletonDoubleValid == null) {
                    singletonDoubleValid = new SingletonDoubleValid();
                }
            }
        }
        return singletonDoubleValid;
    }


}
