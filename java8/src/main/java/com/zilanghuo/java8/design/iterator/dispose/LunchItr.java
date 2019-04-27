package com.zilanghuo.java8.design.iterator.dispose;

import com.zilanghuo.java8.design.iterator.MenuItem;

/**
 * @author laiwufa
 * @date 2019/4/27 22:40
 */
public class LunchItr implements MenuItaraBle {

    public MenuItem arr[];

    private int curson = -1;

    public LunchItr(MenuItem[] arr) {
        this.arr = arr;
    }

    @Override
    public MenuItem next() {
        return arr[curson - 1];
    }

    @Override
    public Boolean hashNext() {
        if (curson == -1) {
            curson = 0;
        }
        curson++;
        if (curson <= arr.length) {
            return true;
        }
        return false;
    }
}
