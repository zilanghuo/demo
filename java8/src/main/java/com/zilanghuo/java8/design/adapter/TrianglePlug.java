package com.zilanghuo.java8.design.adapter;

/**
 * @author laiwufa
 * @date 2019/4/8 0008 下午 3:52
    三角形两头插头
 */
public class TrianglePlug implements TwoPlug {

    @Override
    public void twoJack() {
        System.out.println("这个是二孔插头的方法");
    }
}
