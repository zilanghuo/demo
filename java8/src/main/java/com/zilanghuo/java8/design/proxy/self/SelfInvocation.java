package com.zilanghuo.java8.design.proxy.self;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author laiwufa
 * @date 2019/5/17 0017 下午 4:31
 */
public class SelfInvocation {

    private Object[] params;

    private Method method;

    private Object target;

    // 反射方法
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, method);
    }

}
