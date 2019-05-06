package com.zilanghuo.java8.listdemo;

import java.util.*;

/**
 * @author laiwufa
 * @date 2019/5/5 0005 下午 2:30
 */
public class ListDemo {

    public static final int SIZE = 500000;

    public static void main(String[] args) {
        ListDemo demo = new ListDemo();
        ArrayList list = demo.initArrayList();
        LinkedList linkedList = demo.initLinkedList();
        demo.addCostTime(list); // 69702
        demo.addCostTime(linkedList); // 75
        // demo.readCostTime(list);
        // demo.readCostTime(linkedList);
        // demo.readRandomCostTime(list); // 41
        // demo.readRandomCostTime(linkedList); // 263282
        //demo.deleteByPosition(list); // 22761
        // demo.deleteByPosition(linkedList); // 8
        // demo.loopTest(list);
        // demo.loopTest(linkedList);
    }


    // 判断指定元素插入的时间耗时
    public void addCostTime(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.add(0, i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("插入耗时：" + (end.getTime() - start.getTime()));
    }

    // 读取元素耗时
    public void readCostTime(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.get(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("读取耗时：" + (end.getTime() - start.getTime()));
    }

    // 随机读取元素50000ci
    public void readRandomCostTime(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            int j = new Random().nextInt(SIZE);
            list.get(j);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("随机读取耗时：" + (end.getTime() - start.getTime()));
    }

    public void deleteByPosition(List list) {
        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.remove(0);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("指定位置删除耗时：" + (end.getTime() - start.getTime()));
    }

    public ArrayList initArrayList() {
        Date start = Calendar.getInstance().getTime();
        ArrayList list = new ArrayList();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("arrayList初始化耗时：" + (end.getTime() - start.getTime()));
        return list;
    }

    public LinkedList initLinkedList() {
        Date start = Calendar.getInstance().getTime();
        LinkedList list = new LinkedList();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("linkedList初始化耗时：" + (end.getTime() - start.getTime()));
        return list;
    }

    public void loopTest(List list) {

        Date startTwo = Calendar.getInstance().getTime();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        Date endTwo = Calendar.getInstance().getTime();
        System.out.println("ite循环耗时：" + (endTwo.getTime() - startTwo.getTime()));

        Date start = Calendar.getInstance().getTime();
        for (int i = 0; i < SIZE; i++) {
            list.get(i);
        }
        Date end = Calendar.getInstance().getTime();
        System.out.println("for循环耗时：" + (end.getTime() - start.getTime()));

    }

}
