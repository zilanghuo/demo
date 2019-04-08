package com.zilanghuo.java8.design.adapter;

/**
 * @author laiwufa
 * @date 2019/4/8 0008 下午 3:53
    圆形三孔插座
 */
public class RoundThreePlug implements ThreePlug {

    @Override
    public void threePlugIn() {
        System.out.println("这是三孔插头充电方法！");
    }

    @Override
    public void out() {
        System.out.println("这是三孔特有的方法");
    }
}
