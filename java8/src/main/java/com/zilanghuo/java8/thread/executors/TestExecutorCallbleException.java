package com.zilanghuo.java8.thread.executors;

import java.util.concurrent.*;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 4:06
 */
public class TestExecutorCallbleException {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque(4));
        for (int i = 0; i < 3; i++) {
            try {
                Future<String> submit = executor.submit(new ProcessCallable(i));
            }catch (Exception e){
                System.out.println(e.getClass());
            }

        }
        System.out.println("主程序");
    }


    static class ProcessCallable implements Callable<String> {

        private int size;

        ProcessCallable(int size) {
            this.size = size;
        }

        @Override
        public String call() throws Exception {
            if (size == 1) {
                int a = 1 / 0;
            } else {
                System.out.println("正常执行：i" + size);
            }
            return "hello";
        }
    }

}
