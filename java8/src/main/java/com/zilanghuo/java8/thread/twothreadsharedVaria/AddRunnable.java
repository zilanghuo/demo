package com.zilanghuo.java8.thread.twothreadsharedVaria;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 上午 9:43
 */
public class AddRunnable implements Runnable {

    MyData data;

    public AddRunnable(MyData data){
        this.data = data;
    }

    @Override
    public void run() {
        data.add();
    }
}
