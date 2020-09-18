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

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        String pattern = "[a-zA-Z]*?[1000-3999][a-zA-Z]?";
        int position = 0;
        for (String s : strings) {
            if (Pattern.matches(pattern,s)) {
                if (position == 0) {
                    System.out.println(s);
                    position++;
                } else {
                    System.out.println(" " + s);
                }
            }
        }
    }
}
