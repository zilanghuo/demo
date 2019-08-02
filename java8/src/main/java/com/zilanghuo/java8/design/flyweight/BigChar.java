package com.zilanghuo.java8.design.flyweight;

import lombok.Data;

/**
 * @author laiwufa
 * @date 2019/8/2 9:07
 */
@Data
public class BigChar {
    private Integer charName;
    private String fontName;
    public void print() {
        System.out.println("charName:" + charName + ",instance:" + this.hashCode());
    }
}
