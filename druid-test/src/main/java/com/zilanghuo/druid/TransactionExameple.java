package com.zilanghuo.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author laiwufa
 * @date 2019/5/29 0029 下午 1:59
 */
public class TransactionExameple {

    public static String strB = "update student set age = age + 1 where id = 1";

    public static String strA = "select age from student where id = 1";

    public static void main(String[] args) throws Exception {

        final Boolean[] isOver = {false};
        //start transaction with consistent snapshot
        // 三个事务：A
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBUtil dbUtil = new DBUtil();
                Connection connect = dbUtil.getConnect();
                try {
                    // 第一步
                    connect.prepareStatement(strA).executeQuery();
                    Thread.sleep(50 * 1000); // 跳到b
                    // 最后一步
                    PreparedStatement pstmt = connect.prepareStatement(strA);
                    ResultSet resultSet = pstmt.executeQuery();
                    while (resultSet.next()) {
                        System.out.println("A:age:" + resultSet.getInt(1));
                    }
                    System.out.println("a commit");
                    dbUtil.commit(connect);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        //事务b
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBUtil dbUtil = new DBUtil();
                Connection connect = dbUtil.getConnect();
                try {
                    Thread.sleep(2 * 1000);
                    PreparedStatement pstmt = connect.prepareStatement(strA);
                    ResultSet resultSet = pstmt.executeQuery();
                    while (resultSet.next()) {
                        System.out.println("更新前：age:" + resultSet.getInt(1));
                    }
                    Thread.sleep(3000);
                    //第二步
                    int i = connect.createStatement().executeUpdate(strB);
                    System.out.println("B:更新完毕" + i);
                    // 第三步查询
                    ResultSet rest = connect.prepareStatement(strA).executeQuery();
                    while (rest.next()) {
                        System.out.println("更新后：age:" + rest.getInt(1));
                    }
                    isOver[0] = true;
                    Thread.sleep(15000);
                    System.out.println("b commit");
                    dbUtil.commit(connect);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


        //事务c
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBUtil dbUtil = new DBUtil();
                Connection connect = dbUtil.getConnect();
                try {
                    Thread.sleep(3 * 1000);
                    connect.createStatement().executeUpdate(strB);
                    System.out.println("c update");
                    // c 暂停提交
                    Thread.sleep(30 * 1000);
                    System.out.println("c commit");
                    dbUtil.commit(connect);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


        Thread.sleep(40 * 1000);
    }
}
