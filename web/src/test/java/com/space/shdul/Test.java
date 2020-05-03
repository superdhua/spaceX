package com.space.shdul;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Test {
    public static void main(String[] args) throws JSONException {
        double x = Math.max(2, 5);
        System.out.println("x = " + x);

        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            System.out.println("r.nextInt(100) = " + r.nextInt(100));
        }
        r.ints();
    }
}
