package com.zilanghuo.java8.thread;

import java.util.Vector;

/**
 * vectore 线程不安全
 */
public class VectorNotThreadSafe {

    private static Vector<Integer> vector = new Vector();

    public static void main(String[] args) {

        while (true){
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread addThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                }
            });
            removeThread.start();
            addThread.start();
            while (Thread.activeCount() > 20);

        }
    }
}
