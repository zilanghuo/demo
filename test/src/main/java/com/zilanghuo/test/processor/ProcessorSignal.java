package com.zilanghuo.test.processor;

/**
 * Created by laiwufa on 2020-07-15
 */
public class ProcessorSignal {

    public static void main(String[] args) throws Exception {
        KillSignalHandler operateSignalHandler = new KillSignalHandler();
        // 注册对指定信号的处理
        //  Signal.handle(new Signal("TERM") ,operateSignalHandler);    // kill or kill -15
//        Signal.handle(new Signal("INT"), operateSignalHandler);     // kill -2
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("关闭");
            }
        }));
        System.out.println("[Thread:" + Thread.currentThread().getName() + "] is sleep");
        while (true) Thread.sleep(1000);
    }
}
