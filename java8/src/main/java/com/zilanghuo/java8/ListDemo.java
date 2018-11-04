package com.zilanghuo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author laiwufa
 * @date 2018/10/31
 * use:
 */
public class ListDemo {

    @org.junit.Test
    public void copyOnWriteArrayList() {
        List<Long> arrayList = new ArrayList();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20 * 10000; i++) {
            arrayList.add(System.currentTimeMillis());
        }
        List<Long> arrayListToCopy = new CopyOnWriteArrayList(arrayList);
        // 22464596
        System.out.println("arrayList cost:" + (System.currentTimeMillis() - start));

        List<Long> copyOnWriteArrayList = new CopyOnWriteArrayList(arrayList);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 20 * 10000; i++) {
            copyOnWriteArrayList.add(System.currentTimeMillis());
        }
        // 52130161129
        System.out.println("copyOnWriteArrayList cost:" + (System.currentTimeMillis() - start2));

    }


    @org.junit.Test
    public void subListFailFast() {
        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List branchList = masterList.subList(0, 3);
        // 通过sublist获取到子集，如果此时再去更改主集，会导致子集的报错
        //masterList.remove(0);
        //masterList.add("ten");
        //masterList.clear();

        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        for (Object o : branchList) {
            System.out.println(o);
        }
        // 子集的改变，也会改变主集
        System.out.println(masterList);

    }

}
