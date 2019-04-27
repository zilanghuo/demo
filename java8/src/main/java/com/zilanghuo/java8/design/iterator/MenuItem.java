package com.zilanghuo.java8.design.iterator;

import java.math.BigDecimal;

/**
 * @author laiwufa
 * @date 2019/4/27 22:25
 * 菜单明细
 */
public class MenuItem {

    private String name;

    private BigDecimal price;

    public MenuItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name:" + name + ",price:" + price;
    }
}
