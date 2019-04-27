package com.zilanghuo.java8.design.iterator.dispose;

import com.zilanghuo.java8.design.iterator.MenuItem;

import java.util.List;

/**
 * @author laiwufa
 * @date 2019/4/27 22:40
 */
public class DinnerItr implements MenuItaraBle {

    private List<MenuItem> menuList;

    private int curson = -1;

    public DinnerItr(List<MenuItem> menuList) {
        this.menuList = menuList;
    }


    @Override
    public MenuItem next() {
        return menuList.get(curson -1);
    }

    @Override
    public Boolean hashNext() {
        if (curson == -1) {
            curson = 0;
        }
        curson++;
        if (curson <= menuList.size()) {
            return true;
        }
        return false;
    }
}
