package com.zilanghuo.java8.innerclass;

import lombok.Data;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 下午 4:38
 */
@Data
public abstract class Bird {
    private String name;
    public abstract int fly();
}
