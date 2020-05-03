package com.space.controller;

import com.alone.common.utils.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/test")
public class TestContro {


    @PostMapping("/testConn")
    public Map test1(HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin","*");




        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("sex",1);
        System.out.println("map");
        return map;

    }
    @PostMapping("/test2")
    public Map test2(HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("sex",1);
        System.out.println("map");
        return map;

    }
    @PostMapping("/test3")
    public List<Map<String, Object>> test3(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.addHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("sex",1);
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        System.out.println("map");
        return list;

    }
    @PostMapping("/test4")
    public List<Map<String, Object>> test4(HttpServletResponse response, HttpServletRequest request) throws IOException {
       String name = (String) request.getAttribute("name");
        response.addHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        System.out.println("map");
        return list;
    }


}
