package com.zilanghuo.druid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author laiwufa
 * @date 2019/5/29 0029 下午 1:54
 */
public class DBUtil {

    //数据库的连接的URL
    private static String url = "jdbc:mysql://172.17.34.119:3306/yugong_laiwufa";
    //数据库用户名
    private static String user = "root";
    //数据库密码
    private static String password = "password";

    /**
     * 获取连接
     * @return
     */
    public Connection getConnect(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取连接
     * @return
     */
    public  void commit(Connection conn){
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
