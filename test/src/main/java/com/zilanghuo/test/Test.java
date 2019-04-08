package com.zilanghuo.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.JarOutputStream;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            final int subNo = i;

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                    }
                    System.out.println(subNo);
                }
            });

        }


    }

    static int test() {
        int i = 0 ;
        try {
            return i;
        } catch (Exception e) {
            return -1;
        } finally {
            i++;
        }
    }

    void getOne(){
        return;
    }

    static void a(){
    }

}
