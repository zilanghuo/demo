package com.zilanghuo.java8.design.observe;

/**
 * @author laiwufa
 * @date 2019/3/19 0019 下午 5:30
 * 观察者模式：
 * 1、一个主题，两个观察者
 * 2、所有的观察者，需要被动收到主题获取的数据，不可以自定义筛选
 */
public class Test {
    public static void main(String[] args) {
        WeatherSubject subject = new WeatherSubject();
        IObserve tvObserve = new TvObserve(subject);
        IObserve workObserve = new WorkObserve(subject);
        //触发温度变更
        subject.updateTemp(5);

    }

}
