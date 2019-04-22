package com.zilanghuo.java8.thread.twothreadsharedVaria;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 上午 9:57
 */
public class DecRunnable implements Runnable {

    MyData data;

    public DecRunnable(MyData data){
        this.data = data;
    }

    @Override
    public void run() {
        data.desc();
    }
}
