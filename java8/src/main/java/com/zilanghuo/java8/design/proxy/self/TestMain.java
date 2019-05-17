package com.zilanghuo.java8.design.proxy.self;

import com.zilanghuo.java8.design.proxy.ITakeService;
import com.zilanghuo.java8.design.proxy.TakeService;

/**
 * @author laiwufa
 * @date 2019/5/17 0017 下午 4:38
 */
public class TestMain {
    public static void main(String[] args) {
        ITakeService takeService = new TakeService();
    }

}
