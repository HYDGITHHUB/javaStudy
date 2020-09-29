package com.pong.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    public static void main(String[] args) {
        String pattern = "";
        String str = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println(m.group(0));
        }
        if (m.matches()) {
            System.out.println(str);
        }
    }
}
