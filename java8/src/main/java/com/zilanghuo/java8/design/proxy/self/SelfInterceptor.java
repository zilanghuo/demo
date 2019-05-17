package com.zilanghuo.java8.design.proxy.self;

import java.lang.reflect.InvocationTargetException;

/**
 * @author laiwufa
 * @date 2019/5/17 0017 下午 4:28
 */
public interface SelfInterceptor {
    // 事前方法
    public boolean before();

    // 事后方法
    public void after();

    // 取代原来的方法
    public Object around(SelfInvocation invocation) throws InvocationTargetException, IllegalAccessException;

    // 是否返回方法 。 事件没有发生异常执行
    public void afterReturning();

    // 事后异常方法 ， 当事件发生异常后执行
    public void afterThrowing();

    // 是否使用 around 方法取代原有方法
    boolean useAround();

}
