package com.zilanghuo.test.processor;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by laiwufa on 2020-07-15
 * @author admin
 */
public class KillSignalHandler implements SignalHandler {
    @Override
    public void handle(Signal signal) {
        // 信号量名称
        String name = signal.getName();
        // 信号量数值
        int number = signal.getNumber();

        // 当前进程名
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[Thread:"+currentThreadName + "] receved signal: " + name + " == kill -" + number);
        if(name.equals("TERM")){
            System.exit(0);
        }
    }
}
