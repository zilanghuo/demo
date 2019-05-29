package com.zilanghuo.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author laiwufa
 * @date 2019/5/29 0029 下午 2:38
 */
public class Test {

    public static String strB = "update student set age = age + 1 where id = 1";

    public static String strA = "select age from student where id = 1";

    public static void main(String[] args) {
        DBUtil dbUtil = new DBUtil();
        Connection connect = dbUtil.getConnect();
        try {
            PreparedStatement pstmt = connect.prepareStatement(strA);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("更新前：age:" + resultSet.getInt(1));
            }
            //第二步
            int i = connect.createStatement().executeUpdate(strB);
            System.out.println("B:更新完毕" + i);
            // 第三步查询
            ResultSet rest = connect.prepareStatement(strA).executeQuery();
            while (rest.next()) {
                System.out.println("更新后：age:" + rest.getInt(1));
            }
            System.out.println("b commit");
            dbUtil.commit(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
