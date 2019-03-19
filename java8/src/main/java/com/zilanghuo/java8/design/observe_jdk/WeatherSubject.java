package com.zilanghuo.java8.design.observe_jdk;

import com.zilanghuo.java8.design.observe.IObserve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 5:31
 * 天气主题，包含天气的数据
 */
public class WeatherSubject extends Observable {

    /**
     * 变更温度
     */
    public void updateTemp(Integer temp) {
        System.out.println("变更的温度：" + temp);
        setChanged();
        notifyObservers();
    }


}
