package com.zilanghuo.java8.thread.readWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 上午 11:45
 */
public class QueueData {

    private Object data = null;
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " be ready to read data!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + " have  read data:" + data);
        rwl.readLock().unlock();
    }

    public void put(Object data) {
        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " be ready to write data!");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        this.data = data;
        System.out.println(Thread.currentThread().getName() + " have write data: " + data);
        rwl.writeLock().lock();
    }

}
