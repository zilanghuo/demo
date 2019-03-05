package com.zilanghuo.java8.proxy.staticProxy;

import com.zilanghuo.java8.proxy.ITakeService;
import com.zilanghuo.java8.proxy.TakeService;

/**
 * @author laiwufa
 * @date 2018/9/23
 * use:
 */
public class TestMain {
    public static void main(String[] args) {
        ITakeService realTakeService = new TakeService();
        ITakeService proxyService = new TakeProxyService(realTakeService);
        proxyService.sayHello();
    }
}
