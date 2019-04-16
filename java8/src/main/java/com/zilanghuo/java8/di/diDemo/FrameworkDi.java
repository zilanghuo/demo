package com.zilanghuo.java8.di.diDemo;

/**
 * @author laiwufa
 * @date 2019/4/16 0016 上午 11:09
 */
public class FrameworkDi {
    private BottomDi bottom;
    FrameworkDi(BottomDi bottom) {
        this.bottom = bottom;
    }
}
