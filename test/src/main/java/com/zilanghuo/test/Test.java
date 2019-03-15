package com.zilanghuo.test;

import java.util.List;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    public static void main(String[] args) {
        int totalSize = 2345;
        int pageSize = 1000;
        int retNum = totalSize / pageSize;
        int modNum = totalSize % pageSize;
        int times = retNum + (modNum > 0 ? 1 : 0);    //发送次数

        for (int groupNo = 0; groupNo < times; groupNo++) {
            System.out.println("-------------");

        }






    }

}
