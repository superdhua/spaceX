package com.alone.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCFactory {
    public  static Connection getConn(String type,String urls,String username,String password) {
        Connection conn = null;
        String url="";
        String driver="";
        if ("orcl".equals(type)){
            driver = "oracle.jdbc.OracleDriver";
            url = "jdbc:oracle:thin:@"+urls;
        }else {
            driver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://"+urls;
        }
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
    public static void main(String[] args) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "select table_name as tablename from user_tables where TABLESPACE_NAME is not null and  user='ZJT'";
        try {
            conn =  JDBCFactory.getConn("","rm-2ze80l7e7rm3sov641o.mysql.rds.aliyuncs.com:3306/dblink?serverTimezone=GMT%2B8&amp",
//                    "spacex","Weilai1126");
                    "superdhua","Weilai1126");
            System.out.println("sql = " + conn);
//            conn.setAutoCommit(false);
//            ps = conn.prepareStatement(sql);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()){
//               list.add(resultSet.getString("tablename"));
//            }
//            System.out.println("list = " + list);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null) {conn.close();}
            if (ps != null) ps.close();
        }
    }
}
