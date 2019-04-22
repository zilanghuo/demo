package com.zilanghuo.java8.thread.twothreadsharedVaria;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 上午 9:58
 */
public class TestMain {

    public static void main(String[] args) {
        MyData data = new MyData();
        Runnable addRunnable = new AddRunnable(data);

        Runnable decRunnable = new DecRunnable(data);

        for (int i = 0; i < 2; i++) {
            new Thread(addRunnable).start();
            new Thread(decRunnable).start();
        }


    }
}
