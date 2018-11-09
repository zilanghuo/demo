package com.zilanghuo.java8.calculate;

/**
 * @author laiwufa
 * @date 2018/11/9
 * use: for return 语法
 */
public class Circulate {

    public static void main(String[] args) {
        /**
         * retry 用法，当i==3时，跳到retry，当i此时的值为3
         */
        retry:
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                continue retry;
            }
            System.out.println(i);
        }
        System.out.println("--------------------------");
        /**
         * retyr：后面跟随的是循环
         */
        for (int i = 0; i < 5; i++) {
            //     retry2:
            while (i == 3) {
                System.out.println("continue 3");
                //           continue retry2;
            }
            System.out.println(i);
        }
        System.out.println("--------------------------");


        /**
         * break，终止循环
         */
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            if (i == 3) {
                break;
            }
        }
        System.out.println("---------------------------");
        /**
         * continue 继续执行该循环
         */
        for (int i = 1; i < 5; i++) {
            System.out.println("--");
            if (i % 2 == 0) {
                System.out.println(i);
                continue;
            }

        }

        /**
         * for循环，return 跳出整个循环
         */
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            if (i == 3) {
                return;
            }
        }

    }

}
