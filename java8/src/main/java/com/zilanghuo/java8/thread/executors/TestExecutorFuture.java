package com.zilanghuo.java8.thread.executors;

import java.util.concurrent.*;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
 */
public class TestExecutorFuture {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                60L, TimeUnit.SECONDS, new LinkedBlockingDeque(4));

        Callable myCallable = () -> {
            Thread.sleep(3000);
            System.out.println("calld方法执行了");
            return "call方法返回值";
        };
        Future future = executor.submit(myCallable);
        System.out.println("返回结果：" + future.get());
    }

}
