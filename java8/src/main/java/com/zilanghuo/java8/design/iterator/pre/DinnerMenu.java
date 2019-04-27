package com.zilanghuo.java8.design.iterator.pre;

import com.zilanghuo.java8.design.iterator.MenuItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author laiwufa
 * @date 2019/4/27 22:24
 * 晚餐菜单，通过list实现
 */
public class DinnerMenu {

    public static List<MenuItem> menuList = new ArrayList();

    static {
        menuList.add(new MenuItem("晚餐1",new BigDecimal(11)));
        menuList.add(new MenuItem("晚餐2",new BigDecimal(22)));
        menuList.add(new MenuItem("晚餐3",new BigDecimal(33)));
        menuList.add(new MenuItem("晚餐4",new BigDecimal(44)));
        menuList.add(new MenuItem("晚餐5",new BigDecimal(55)));
    }

}
