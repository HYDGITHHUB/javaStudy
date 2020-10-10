package com.pong.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test11 {
    public static void main(String[] args) {
        String pattern = "";
        String str = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            count++;
            System.out.println(111);
            System.out.println(m.group(0));
            System.out.println(222);
        }
        if (count == 0) {
            System.out.println(333);
        }
    }
}
