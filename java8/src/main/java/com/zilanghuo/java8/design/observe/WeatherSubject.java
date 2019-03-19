package com.zilanghuo.java8.design.observe;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 5:31
 * 天气主题，包含天气的数据
 */
public class WeatherSubject {

    private ArrayList<IObserve> observeList = new ArrayList();

    /**
     * 增加监听器
     *
     * @param observe
     */
    public void addListener(IObserve observe) {
        observeList.add(observe);
    }

    /**
     * 减少监听器
     *
     * @param observe
     */
    public void removeListener(IObserve observe) {
        observeList.remove(observe);
    }

    /**
     * 变更温度
     */
    public void updateTemp(Integer temp) {
        System.out.println("变更的温度：" + temp);
        Iterator<IObserve> observeItr = observeList.iterator();
        while (observeItr.hasNext()) {
            observeItr.next().monitor();
        }
    }


}
