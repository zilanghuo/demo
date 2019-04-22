package com.zilanghuo.java8.thread.twothreadsharedVaria;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 上午 9:41
 */
public class MyData {

    private int j = 0;

    public synchronized void add() {
        j++;
        System.out.println("线程：" + Thread.currentThread().getName() + "j为" + j);
    }

    public synchronized void desc() {
        j--;
        System.out.println("线程：" + Thread.currentThread().getName() + "j为" + j);
    }

    public int getData() {
        return j;
    }

}
