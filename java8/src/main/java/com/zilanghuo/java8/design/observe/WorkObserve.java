package com.zilanghuo.java8.design.observe;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 5:32
 */
public class WorkObserve implements IObserve {

    private WeatherSubject weatherSubject;

    WorkObserve(WeatherSubject weatherSubject){
        this.weatherSubject = weatherSubject;
        weatherSubject.addListener(this);
    }

    @Override
    public void monitor() {
        System.out.println("这是work-monitor 方法");
    }
}
