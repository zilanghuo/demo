package com.zilanghuo.java8.jvm;

/**
 * -Xss128k 栈深度过大
 * @author laiwufa
 * @date 2019/4/3 0003 下午 1:59
 */
public class StackOverFlowErrorDemo {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlowErrorDemo stackOverFlowError = new StackOverFlowErrorDemo();
        try {
            stackOverFlowError.stackLeak();
        } catch (Exception e) {
            System.out.println("stackLength:" + stackOverFlowError.stackLength);
            throw e;
        }
    }

}
