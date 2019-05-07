package com.zilanghuo.java8.listdemo;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * @author laiwufa
 * @date 2019/5/7 0007 下午 3:47
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap(8);
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            hashMap.put(i, i + 10);
        }
        for (int i = 0; i < 10; i++) {
            mockNotSafe();
        }
    }

    /**
     * 模拟线程不安全，通过resize触发模拟
     */
    public static void mockNotSafe() {
        HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);
        map.put(5, "C");
        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, "B");
            }
        }.start();
        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, "A");
            }
        }.start();
        System.out.println(JSONUtil.toJsonStr(map));
    }
}
