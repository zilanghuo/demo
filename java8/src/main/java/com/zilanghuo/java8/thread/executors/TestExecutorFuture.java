package com.zilanghuo.java8.thread.executors;

import java.util.concurrent.*;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
 */
public class TestExecutorFuture {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,60L, TimeUnit.SECONDS, new LinkedBlockingDeque(4));

        Future future = executor.submit(new TestCallable());
        try {
            System.out.println("返回结果：" + future.get(4,TimeUnit.SECONDS));
        }catch (Exception e){
            future.cancel(true);
        }
        Future<?> submit = executor.submit(new TestRunnable());
        System.out.println(submit.get()+"---");

    }

    static class TestCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("执行结果中--------------");
            // int a = 1/0;
            return "未超时结果";
        }
    }

    static class TestRunnable implements Runnable{

        @Override
        public void run() {
            int a =  1/0;
        }
    }

}

