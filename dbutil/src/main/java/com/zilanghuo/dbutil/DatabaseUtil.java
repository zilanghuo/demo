package com.zilanghuo.dbutil;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * @date 2020/4/5
 */
public class DatabaseUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.0.55.221:3306/pallas-lai?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "mss_uat";
    private static final String PASSWORD = "mss_uat";


    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void exeuteSql(List<String> sql) throws Exception{
        if (null == sql || "".equals(sql)){
            return;
        }
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        for (String str : sql){
            System.out.println("-----");
            System.out.println(str);
            try {
                pStemt = conn.prepareStatement(str);
                pStemt.execute();
            }catch (Exception e){

            }

        }
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName
     * @return
     */
    public static List<ColumnDTO> getColumns(String tableName) {
        List<ColumnDTO> columnDTOList = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                ColumnDTO columnDTO = new ColumnDTO();
                columnDTO.setColumnName(rsmd.getColumnName(i + 1));
                columnDTO.setColumnType(rsmd.getColumnTypeName(i + 1));
                columnDTOList.add(columnDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnDTOList;
    }

    /**
     * 获取表中字段的所有注释
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnComments;
    }

    public static List<Map<String, Object>> selectAll(String table, Integer limit) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        String sql = "select * from " + table;
        if (limit != null) {
            sql += " limit " + limit;
        }
        Connection connection = getConnection();
        if (connection != null && !connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    map.put(rs.getMetaData().getColumnName(i + 1), rs.getObject(i + 1));
                }
                resultList.add(map);
            }
            rs.close();
            connection.close();
        }
        return resultList;
    }

    public static List<Map<String, Object>> sql(String sqlExecute) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Connection connection = getConnection();
        if (connection != null && !connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlExecute);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    map.put(rs.getMetaData().getColumnName(i + 1), rs.getObject(i + 1));
                }
                resultList.add(map);
            }
            rs.close();
            connection.close();
        }
        return resultList;
    }

    public static void main(String[] args) throws Exception {
        // 今天的实例
        List<Map<String, Object>> sqlToday = sql("SELECT task_name,plan_start_time,real_start_time,real_end_time FROM task_record where plan_start_time >= '2021-08-24 00:00:01' and plan_start_time <= '2021-08-24 12:00:01' and run_status = 50 order by plan_start_time asc");
        Map<String, Map<String, Object>> taskPlanMap = new HashMap<>();
        sqlToday.stream().forEach(map -> {
            Map<String, Object> map1 = taskPlanMap.get(map.get("task_name") + "");
            if (null == map1) {
                taskPlanMap.put(map.get("task_name") + "", map);
            }
        });
        System.out.println(sqlToday.size());
        //遍历
        Iterator<Map.Entry<String, Map<String, Object>>> iteratorPre = taskPlanMap.entrySet().iterator();
        while (iteratorPre.hasNext()) {
            Map.Entry<String, Map<String, Object>> next = iteratorPre.next();
            System.out.println(JSON.toJSONString(next.getValue()));
        }


        // 7天前的实例
        List<Map<String, Object>> sqlWeekYesToday = sql("SELECT task_name,plan_start_time,real_start_time,real_end_time FROM task_record where plan_start_time >= '2021-08-17 00:00:01' and plan_start_time <= '2021-08-17 12:00:01' and run_status = 50 order by plan_start_time asc");
        Map<String, Map<String, Object>> taskPlanWeekYesToday = new HashMap<>();
        sqlWeekYesToday.stream().forEach(map -> {
            Object object = map.get("plan_start_time");
            Date date = (Date) object;
            String format = new SimpleDateFormat("HH:mm:ss").format(date);

            Map<String, Object> map1 = taskPlanWeekYesToday.get(map.get("task_name") + "");
            if (null == map1) {
                taskPlanWeekYesToday.put(map.get("task_name") + "", map);
            }
        });
        System.out.println(sqlWeekYesToday.size());

        //遍历今天的实例，获取7天前的时间点
        Iterator<Map.Entry<String, Map<String, Object>>> iterator = taskPlanMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, Object>> next = iterator.next();
            Map<String, Object> value = next.getValue();

            Object object = value.get("plan_start_time");
            Date date = (Date) object;
            String format = new SimpleDateFormat("HH:mm:ss").format(date);

            String key = value.get("task_name") + "";
            Map<String, Object> map = taskPlanWeekYesToday.get(key);
            if (null != map) {
                value.put("7RealStart", map.get("real_start_time"));
                value.put("7RealEnd", map.get("real_end_time"));
                taskPlanWeekYesToday.remove(key);
            }
        }

        //遍历
        /*Iterator<Map.Entry<String, Map<String, Object>>> iterator2 = taskPlanMap.entrySet().iterator();
        while (iterator2.hasNext()){
            Map.Entry<String, Map<String, Object>> next = iterator2.next();
            System.out.println(JSON.toJSONString(next.getValue()));
        }*/

        writeWithoutHead(taskPlanMap);


    }

    public static void writeWithoutHead(Map<String, Map<String, Object>> taskPlanMap) throws IOException {
        try (OutputStream out = new FileOutputStream("/Users/admin/Desktop/作业.xls");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("20190304-20190310");
            List<List<Object>> data = new ArrayList<>();

            Iterator<Map.Entry<String, Map<String, Object>>> iterator2 = taskPlanMap.entrySet().iterator();
            int first = 0;
            while (iterator2.hasNext()) {
                Map.Entry<String, Map<String, Object>> next = iterator2.next();
                List<Object> item = new ArrayList<>();
                if (first == 0) {
                    List<Object> headItem = new ArrayList<>();
                    headItem.add("作业名");
                    headItem.add("计划启动时间（24）");
                    headItem.add("启动时间(24)");
                    headItem.add("结束时间（24）");
                    headItem.add("耗时分钟（24）");
                    headItem.add("启动时间（17）");
                    headItem.add("结束时间（17）");
                    headItem.add("耗时分钟（17）");
                    headItem.add("时间相差分钟（24号-17号）");
                    data.add(headItem);
                }
                first = 1;
                Map<String, Object> map = next.getValue();

                if (null != map.get("7RealStart")) {
                    item.add(map.get("task_name"));
                    Date planStartTime = (Date) map.get("plan_start_time");
                    item.add(format(planStartTime));

                    Date realStartTime = (Date) map.get("real_start_time");
                    item.add(format(realStartTime));

                    Date realEndTime = (Date) map.get("real_end_time");
                    item.add(format(realEndTime));
                    long e = (realEndTime.getTime() - realStartTime.getTime()) / 1000 / 60;
                    item.add(e + "");


                    Date realStartTime2 = (Date) map.get("7RealStart");
                    item.add(format(realStartTime2));

                    Date realEndTime2 = (Date) map.get("7RealEnd");
                    item.add(format(realEndTime2));
                    long e1 = (realEndTime2.getTime() - realStartTime2.getTime()) / 1000 / 60;
                    item.add(e1 + "");

                    item.add((e - e1) + "");
                    data.add(item);

                }

            }
            writer.write1(data, sheet1);
            writer.finish();
        }
    }

    public static String format(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


}
