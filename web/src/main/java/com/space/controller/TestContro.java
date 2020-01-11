package com.space.controller;

import com.alone.common.utils.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestContro {

    @RequestMapping("/t")
    public String test(){
        System.out.println("调用common");
        Date date = new Date();
        String test = DateUtils.format(date);
        return test;

    }
}
