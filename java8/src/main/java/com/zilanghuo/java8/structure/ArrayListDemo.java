package com.zilanghuo.java8.structure;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author laiwufa
 * @date 2019/4/27 20:04
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<String> listOne = new ArrayList();
        listOne.add("a");
        listOne.add("b");
        listOne.add("c");

        List<String> listTwo = new ArrayList();
        listTwo.add("b");
        listTwo.add("d");
        listTwo.add("e");
        // testTwoListDifferent(listOne, listTwo);
        testForAndIterator(listOne);
    }

    /**
     * 求两个集合的差集
     */
    public static List<String> testTwoListDifferent(List<String> listOne, List<String> listTwo) {
        listOne.removeAll(listTwo);
        System.out.println("listOne差集：" + JSONUtil.toJsonStr(listOne));
        return listOne;
    }

    /**
     * 测试for循环和迭代器iterator循环的区别
     *
     * @param list
     */
    public static void testForAndIterator(List<String> list) {
        List<String> listOne = list;
        Iterator<String> iterator = listOne.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator:" + iterator.next());
            //    iterator.remove();
        }
        // for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println("for:" + list.get(i) + ",size:" + list.size());
            list.remove(i);
        }

    }
}
