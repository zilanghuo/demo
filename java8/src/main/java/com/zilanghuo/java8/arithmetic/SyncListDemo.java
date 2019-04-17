package com.zilanghuo.java8.arithmetic;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author laiwufa
 * @date 2019/4/16 0016 下午 2:50
 * 写一个固定容量同步容器，拥有Put和get方法，以及getCount方法， 能够支持两个生产者线程以及10个消费者线程的阻塞调用 使用wait和notify/notifyAll来实现
 */
public class SyncListDemo {

    public static void main(String[] args) {
        SyncListDemo listDemo = new SyncListDemo();
       /* for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 10; j++) {
                            listDemo.get();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 50; j++) {
                        try {
                            listDemo.put(finalI + "-" + j);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }*/


        // 两个生产者线程
        ExecutorService produceExecutor = Executors.newFixedThreadPool(2);
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            produceExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        listDemo.put(finalI + "");
                    } catch (Exception e) {

                    }
                    System.out.println("生产剩余个数：" + listDemo.getCount());
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            consumerExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        listDemo.get();
                    } catch (Exception e) {

                    }
                    System.out.println("消费剩余个数：" + listDemo.getCount());
                }
            });
        }
        System.out.println("总个数：" + listDemo.getCount());

    }


    public Vector<String> list = new Vector();

    public final static int maxSize = 10;

    public synchronized String get() throws Exception {
        while (list.size() == 0) {
            System.out.println("缺货中，无法获取");
            this.wait();
        }
        Thread.sleep(10);
        String remove = list.remove(0);
        System.out.println("出货中：" + remove);
        this.notifyAll();
        return remove;
    }

    public synchronized void put(String str) throws Exception {
        while (list.size() >= maxSize) {
            System.out.println("进货量过大，稍等");
            this.wait();
        }
        Thread.sleep(10);
        list.add(str);
        System.out.println("进货中：" + str);
        this.notifyAll();
    }

    public Integer getCount() {
        return list.size();
    }


}
