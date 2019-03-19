package com.zilanghuo.java8.design.observe;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 5:32
 */
public class TvObserve implements IObserve {

    /**
     * 持久主题
     */
    private WeatherSubject weatherSubject;

    TvObserve(WeatherSubject weatherSubject){
        this.weatherSubject = weatherSubject;
        weatherSubject.addListener(this);
    }

    @Override
    public void monitor() {
        System.out.println("这是tv-monitor 方法");
    }
}
