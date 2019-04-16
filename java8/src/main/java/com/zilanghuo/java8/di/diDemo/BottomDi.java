package com.zilanghuo.java8.di.diDemo;

/**
 * @author laiwufa
 * @date 2019/4/16 0016 上午 11:04
 */
public class BottomDi {
    private TireDi tire;
    BottomDi(TireDi tire){
        this.tire = tire;
    }
}
