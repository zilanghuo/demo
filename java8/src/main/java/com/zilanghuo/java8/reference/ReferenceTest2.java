package com.zilanghuo.java8.reference;

/**
 * @author laiwufa
 * @date 2019/5/15 0015 下午 5:44
 */
public class ReferenceTest2 {

    /**
     * 引用传递
     *
     * @param args
     */
    public static void main(String[] args) {
        Staff x = new Staff("A");
        Staff y = new Staff("B");
        System.out.println(x.name + "--" + y.name);
        swap(x, y);
        System.out.println(x.name + "--" + y.name);

    }

    static void swap(Staff x, Staff y) {
        Staff temp = x;
        x = y;
        y = temp;
        System.out.println(x.name + "--" + y.name);

    }


    static class Staff {
        String name;

        public Staff(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}


