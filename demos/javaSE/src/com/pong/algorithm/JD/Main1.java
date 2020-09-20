package com.pong.algorithm.JD;
//小明想从一段英文短文中提取潜在的年份信息，待匹配的年份的范围为1000年至3999年，包含1000和3999。
//
//        输入一段英文短文，按出现次序输出所提取到的所有可能的年份字符串。
//
//
//
//        输入描述
//        单组输入，输入一段英文短文，可能包含字母大小写，标点符号及空格。（不超过2000个字符）
//
//        输出描述
//        输出所提取到的所有可能的年份字符串，两两之间用一个空格隔开。
//
//
//        样例输入
//        And millionaires will hold 46% of total wealth by 2019, the report says. This ratio is likely to increase in 2020.
//        样例输出
//        2019 2020

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        for (String s : strings) {
            list = isNum(s);
            if (!list.isEmpty()) {
                if (count == 0){
                    System.out.print(list.get(0));
                    for (int i = 1; i < list.size(); i++) {
                        System.out.print(" " + list.get(i));
                    }
                    count++;
                } else {
                    for (String string1 : list) {
                        System.out.println(" " + string1);
                    }
                }
            }
        }


//        String pattern = "[a-zA-Z]*?[1000-3999][a-zA-Z]?";
//        int position = 0;
//        for (String s : strings) {
//            if (Pattern.matches(pattern,s)) {
//                if (position == 0) {
//                    System.out.println(s);
//                    position++;
//                } else {
//                    System.out.println(" " + s);
//                }
//            }
//        }
    }
    public static ArrayList<String> isNum(String s) {
        int count;
        ArrayList<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            count = 0;
            if (chars[i] >= '1' && chars[i] <= '3') {
                while (chars[i+1] >= '0' && chars[i+1] <= '9' && i < chars.length - 1) {
                    count++;
                    i++;
                    if (i >= chars.length-1) break;
                    if (count >= 4) break;;
                }
                if (count == 3) {
                    list.add(s.substring(i-3,i+1));
                }
            }
        }
        return list;
    }
}
