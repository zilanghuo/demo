package com.zilanghuo.druid;

import java.sql.*;

/**
 * @author laiwufa
 * @date 2019/5/11 0011 下午 4:16
 * 建表
 */
public class InsertDemo {

    //数据库的连接的URL
    private static String url = "jdbc:mysql://172.17.34.119:3306/yugong_laiwufa";
    //数据库用户名
    private static String user = "root";
    //数据库密码
    private static String password = "password";


    public static void main(String[] args){
        getRecord();
    }

    /**
     * 插入单条记录，使用？参数化可防止sql注入
     */
    public static void getRecord(){
        Connection connect = getConnect();
        String insertSql = "select * from student where id = ?";
        try {
            PreparedStatement  pstmt = connect.prepareStatement(insertSql);
            pstmt.setInt(1,1);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                System.out.println("id:"+resultSet.getInt(1)+",name:"+resultSet.getString(2)+",gender:"+resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭资源模块代码忽略
    }

    /**
     * 插入单条记录
     */
    public static void insertRecord(){
        Connection connect = getConnect();
        String insertSql = "insert into student(name,gender) values(?,?)";
        try {
            PreparedStatement  pstmt = connect.prepareStatement(insertSql);
            pstmt.setString(1,"小红");
            pstmt.setString(2,"F");
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭资源模块代码忽略
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnect(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createTable(){
        Connection conn = getConnect();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            //4.准备sql语句
            String sql = "CREATE TABLE student(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
            //5.执行sql语句，返回结果
            int count = stmt.executeUpdate(sql);
            System.out.println("影响了"+count+"行");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //6.关闭资源（先关闭statement，再关闭connection）
            if(stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}
