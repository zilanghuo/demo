package com.zilanghuo.java8.design.proxy.self;

import java.lang.reflect.InvocationTargetException;

/**
 * @author laiwufa
 * @date 2019/5/17 0017 下午 4:34
 * 个人拦截器
 */
public class MyInterceptor implements SelfInterceptor {
    @Override
    public boolean before() {
        System.out.println("before");
        return false;
    }

    @Override
    public void after() {
        System.out.println("after");
    }

    @Override
    public Object around(SelfInvocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before");
        Object proceed = invocation.proceed();
        System.out.println("around after");
        return proceed;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    @Override
    public boolean useAround() {
        System.out.println("useAround");
        return false;
    }
}
