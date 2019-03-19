package com.zilanghuo.java8.design.observe_jdk;

import com.zilanghuo.java8.design.observe.IObserve;
import com.zilanghuo.java8.design.observe.WeatherSubject;

import java.util.Observable;
import java.util.Observer;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 5:32
 */
public class TvObserve implements Observer {

    private Observable weatherSubject;

    TvObserve(Observable weatherSubject){
        this.weatherSubject = weatherSubject;
        weatherSubject.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("这是tv-monitor 方法");
    }
}
