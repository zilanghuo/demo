package com.zilanghuo.java8.thread.executors;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 4:06
 */
public class TestExecutorRunnableException {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque(4));
        for (int i = 0; i < 3; i++) {
            executor.submit(new ProcessRunable(i));
        }
        System.out.println("主程序");
    }


    static class ProcessRunable implements Runnable {

        private int size;

        ProcessRunable(int size) {
            this.size = size;
        }

        @Override
        public void run() {
            if (size == 1) {
                int a = 1 / 0;
            } else {
                System.out.println("正常执行：i" + size);
            }
        }
    }

}
