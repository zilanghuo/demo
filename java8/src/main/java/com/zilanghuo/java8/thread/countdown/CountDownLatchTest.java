package com.zilanghuo.java8.thread.countdown;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author laiwufa
 * @date 2018/11/5
 * use:
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        CountDownLatch count = new CountDownLatch(3);
        Thread thread1 = new TranslateThread("1st",count);
        Thread thread2 = new TranslateThread("2st",count);
        Thread thread3 = new TranslateThread("3st",count);
        thread1.start();
        thread2.start();
        thread3.start();
        count.await(10,TimeUnit.SECONDS);
        System.out.println("线程执行完毕。");
    }

}

@Data
@AllArgsConstructor
class TranslateThread extends Thread{

    private String content;
    private final CountDownLatch count;

    @Override
    public void run(){
        if (Math.random() > 0.5){
            throw new RuntimeException("非法字符串");
        }
        System.out.println(content+"的译文。。。。");
        count.countDown();
    }
}