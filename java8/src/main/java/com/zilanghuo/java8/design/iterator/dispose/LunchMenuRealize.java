package com.zilanghuo.java8.design.iterator.dispose;

import com.zilanghuo.java8.design.iterator.MenuItem;

import java.math.BigDecimal;

/**
 * @author laiwufa
 * @date 2019/4/27 22:56
 */
public class LunchMenuRealize implements Iterator {

    public static MenuItem arr[] = new MenuItem[5];

    static {
        for (int i = 0; i < 5; i++) {
            arr[i] = new MenuItem("午餐" + (i + 1), new BigDecimal(33 + i * 3));
        }
    }

    @Override
    public MenuItaraBle createIterator() {
        return new LunchItr(arr);
    }
}
