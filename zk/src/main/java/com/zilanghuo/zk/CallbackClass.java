package com.zilanghuo.zk;

/**
 * @author laiwufa
 * @date 2019/2/25 0025 下午 2:47
 */
public class CallbackClass implements MyCallInterface {
    @Override
    public void method() {
        System.out.println("回调函数");
    }

    public static void main(String[] args) {
        Caller call = new Caller();
        call.setCallfuc(new CallbackClass());
        call.call();
    }
}
