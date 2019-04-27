package com.zilanghuo.java8.design.iterator.pre;

import com.zilanghuo.java8.design.iterator.MenuItem;

import java.util.List;

/**
 * @author laiwufa
 * @date 2019/4/27 22:33
 * 两家合并，需要打印菜单明细，服务员需要知道这两家的具体实现，如果再出现一个订单不同的实现，这边又需要重新写一段具体代码
 */
public class TestMain {
    public static void main(String[] args) {
        List<MenuItem> dinnerList = DinnerMenu.menuList;
        MenuItem[] arr = LunchMenu.arr;
        for (int i = 0; i < dinnerList.size(); i++) {
            System.out.println(dinnerList.get(i));
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
