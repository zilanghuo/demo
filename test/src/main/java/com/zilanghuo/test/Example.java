package com.zilanghuo.test;

public class Example {
    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};
    public static void main(String args[]) {
        Example ex = new Example();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
        ex.change(ex);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }
    /**
     * 值传递
     * @param str
     * @param ch
     */
    public void change(String str, char ch[]) {
        //引用类型变量，传递的是地址，属于引用传递。
        str = "test ok";
        ch[0] = 'g';
    }

    /**
     * 引用传递
     * @param ex2
     */
    public void change(Example ex2) {
        //引用类型变量，传递的是地址，属于引用传递。
        ex2.str = "test ok";
        ex2.ch[0] = 'g';
    }
}
