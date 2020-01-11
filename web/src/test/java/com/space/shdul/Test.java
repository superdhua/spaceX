package com.space.shdul;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws JSONException {
        Set<String> set = new HashSet<>();
        set.add("111");
        set.add("222");
        set.add("122");
        set.add("33");
        System.out.println("set = " + set);
        JSONObject json = new JSONObject();
        json.put("name","zhangsan");
        json.put("age","16");

        List<Set> list = new ArrayList<>();
        List<List> lista = new ArrayList<>();
        List<JSONObject> listb = new ArrayList<>();
        list.add(set);
        list.add(set);
        lista.add(list);
        listb.add(json);
        System.out.println("list = " + list);
        System.out.println("lista = " + lista);
        System.out.println("listb = " + listb);
    }
}
