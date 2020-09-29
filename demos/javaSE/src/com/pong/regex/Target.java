package com.pong.regex;

import java.util.regex.Pattern;

public class Target {
    public static void main(String[] args) {
        String string = "abc 55555 RegExr was creat222ed by 2222gskinner.com, and is prou5555dly hosted by Media Temple.\n" +
                "\n" +
                "Edit the Ex2222pression & 2222 T2333ext to see match222223es. 2456 Roll9 over1 matches or the expression for details. PCRE " +
                "" +
                "& Javascript flavors of 22 RegEx are supported.\n" +
                "\n" +
                "The aabbaba aba abb bbaaaaaabbaabb aa 666 99 9 9999 99999 aab side23 bar includes a Cheatsheet, full Reference, and Help." +
                " You can also Save & Sha233333re with the Com2356munity, and view pat5555terns you create or favorite in My Patterns.\n" +
                "\n" +
                "Exp232222lore results with the Tools below. Replace & List output custom results. Details lists capture groups. Explain describes your expression in plain English.\n";
        String[] split = string.split(" ");
        System.out.println("共计：" + split.length);
        String pattern = "\\D*[1-3][0-9]{3}\\D*";
        String pattern1 = "\\d{1,4}";
        int count = 0;
        System.out.println("字符串匹配：");
        for (String s : split) {
            if (s.matches(pattern)) {
                count++;
                System.out.print(s + "---" + count + "   ");
            }
        }
        System.out.println();
        System.out.println(count);
        count = 0;
        System.out.println("正则表达式匹配：");
        for (int i = 0; i < split.length; i++) {
            if (Pattern.matches(pattern, split[i])) {
                count++;
                System.out.print(split[i] + "---" + count + "   ");
            }
        }
        System.out.println();
        System.out.println(count);
        count = 0;
        System.out.println("for");
        for (String s : split) {
            if (Pattern.matches(pattern, s)) {
                count++;
                System.out.print(s + "---" + count + "   ");
            }
        }
        System.out.println();
        System.out.println(count);
    }
}