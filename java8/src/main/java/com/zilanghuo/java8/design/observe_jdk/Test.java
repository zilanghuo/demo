package com.zilanghuo.java8.design.observe_jdk;

import java.util.Observer;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 6:11
 * java内置的jdk模式
 */
public class Test {

    public static void main(String[] args) {
        WeatherSubject subject = new WeatherSubject();
        Observer tvObserve = new TvObserve(subject);
        Observer workObserve = new WorkObserve(subject);
        //触发温度变更
        subject.updateTemp(5);
    }

}
