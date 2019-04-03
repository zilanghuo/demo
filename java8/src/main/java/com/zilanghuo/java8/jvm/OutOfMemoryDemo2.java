package com.zilanghuo.java8.jvm;

/**-Xss2m
 * OOM:线程创建过多
 * @author laiwufa
 * @date 2019/4/3 0003 下午 2:16
 */
public class OutOfMemoryDemo2 {

    public void doStop(){
        while (true){

        }
    }

    public void stackByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    doStop();
                }
            });
            thread.start();
        }

    }

    public static void main(String[] args) {
        OutOfMemoryDemo2 demo2 = new OutOfMemoryDemo2();
        demo2.stackByThread();
    }


}
