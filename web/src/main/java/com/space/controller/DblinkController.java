package com.space.controller;

import com.alone.jdbc.JDBCFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/dblink")
public class DblinkController {

    @PostMapping("conn")
    public List<String> getAllTable(HttpSession session, HttpServletResponse response) throws SQLException {
        response.addHeader("Access-Control-Allow-Origin","*");
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        JDBCFactory.getConn("orcl","jdbc:oracle:thin:@10.200.45.58:1521/orcl","zjt","CJwJ81e6Su");
        String sql = "select table_name as tablename from user_tables where TABLESPACE_NAME is not null and  user='ZJT'";
        try {
            conn = JDBCFactory.getConn("orcl","jdbc:oracle:thin:@10.200.45.58:1521/orcl","zjt","CJwJ81e6Su");
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString("tablename"));
            }
            session.setAttribute("url","jdbc:oracle:thin:@10.200.45.58:1521/orcl");
            session.setAttribute("username","zjt");
            session.setAttribute("password","CJwJ81e6Su");
//            response.getWriter().write(list.toString());
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null) {conn.close();}
            if (ps != null) ps.close();
        }
        return null;
    }

    @PostMapping("/tname")
    public List<Map<String,Object>> getSomeTable(HttpSession session, HttpServletResponse response) throws SQLException {
        response.addHeader("Access-Control-Allow-Origin","*");
        String sql =
                "select col.TABLE_NAME tablename,col.COLUMN_NAME columnname,col.COMMENTS comments from user_col_comments col where col.TABLE_NAME = ? ";

        List<Map<String,Object>> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        String url = (String) session.getAttribute("url");
        String username = (String) session.getAttribute("username");
        String pwd = (String) session.getAttribute("password");
        try {
            conn = JDBCFactory.getConn("orcl","jdbc:oracle:thin:@10.200.45.58:1521/orcl","zjt","CJwJ81e6Su");
            conn.setAutoCommit(false);
            ps.setString(0,"T_INTERFACE_THIRD_COMPANY");
            ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Map<String,Object> map = new HashMap<>(5);
                map.put("tablename",resultSet.getString("tablename"));
                map.put("columnname",resultSet.getString("columnname"));
                map.put("comments",resultSet.getString("comments"));
                list.add(map);
            }
            session.setAttribute("url","jdbc:oracle:thin:@10.200.45.58:1521/orcl");
            session.setAttribute("username","zjt");
            session.setAttribute("password","CJwJ81e6Su");
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null) {conn.close();}
            if (ps != null) ps.close();
        }
        return null;
    }
}
