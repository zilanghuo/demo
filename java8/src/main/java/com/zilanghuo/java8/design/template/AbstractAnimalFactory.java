package com.zilanghuo.java8.design.template;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
 */
public abstract class AbstractAnimalFactory implements AbstractAnimalEat {

    @Override
    public void eatBefore() {
        System.out.println("eat before");
    }

    @Override
    public abstract void eatMethod();

    @Override
    public void eatAfter() {
        System.out.println("eat after");
    }

    public void eat() {
        eatBefore();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            // 设定超时是2s
            boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
            if (await) {
                System.out.println("耗时内完成");
            } else {
                System.out.println("已经超时，重新处理,注销子线程");
                Thread.currentThread().interrupt();
                Thread.currentThread().getState();
            }
        } catch (InterruptedException e) {
            System.out.println("------interrupted");
        }
        // 具体的实现
        eatMethod();
        countDownLatch.countDown();
        eatAfter();
    }


}
