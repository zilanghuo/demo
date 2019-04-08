package com.zilanghuo.java8.design.adapter;

/**
 * @author laiwufa
 * @date 2019/4/8 0008 下午 3:55
 * 转化器，鸭转化器
 */
public class TwoPlug2ThreeAdapter implements ThreePlug {

    private TwoPlug twoPlug;

    TwoPlug2ThreeAdapter(TwoPlug twoPlug){
        this.twoPlug = twoPlug;
    }

    @Override
    public void threePlugIn() {
        System.out.print("三孔的转化：");
        twoPlug.twoJack();
    }

    @Override
    public void out() {
        System.out.println("三孔的实现方法");
    }
}
