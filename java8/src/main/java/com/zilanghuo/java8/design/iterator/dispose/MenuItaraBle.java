package com.zilanghuo.java8.design.iterator.dispose;

import com.zilanghuo.java8.design.iterator.MenuItem;

/**
 * @author laiwufa
 * @date 2019/4/27 22:41
 * 迭代器接口
 */
public interface MenuItaraBle {
    /**
     * 获取元素
     *
     * @return
     */
    public MenuItem next();

    /**
     * 判断是否还有下一个元素
     *
     * @return
     */
    public Boolean hashNext();

}
