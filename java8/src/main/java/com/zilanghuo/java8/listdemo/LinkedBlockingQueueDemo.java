package com.zilanghuo.java8.listdemo;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author laiwufa
 * @date 2019/5/7 0007 上午 11:25
 */
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws Exception{
        LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue(10);
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.take();
        blockingQueue.take();

    }
}
