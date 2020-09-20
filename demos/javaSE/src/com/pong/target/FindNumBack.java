package com.pong.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNumBack {
    public static void main(String[] args) {

        String matcher = "3333 ab25367c match2222233es 55555 2345 RegExr was creat222ed by 2222gskinner.com, and is prou5555dly hosted by Media Temple.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Edit the Ex2222pression & 2222 T2333ext to see match222223es. 2456 Roll9 over1 matches or the expression for details. PCRE \" +\n" +
                "                \"\" +\n" +
                "                \"& Javascript flavors of 22 RegEx are supported.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"The aabbaba aba abb bbaaaaaabbaabb aa 666 99 9 9999 99999 aab side23 bar includes a Cheatsheet, full Reference, and Help.\" +\n" +
                "                \" You can also Save & Sha233333re with the Com2356munity, and view pat5555terns you create or favorite in My Patterns.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Exp232222lore results with the Tools below. Replace & List output custom results. Details lists capture groups. Explain describes your expression in plain English.\\n";
        matcher = " " + matcher;
        String pattern = "[^\\d]+([1-3][0-9]{3})[^\\d]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(matcher);
//        boolean result = m.matches();
//        System.out.println(result);
        int count = 0;
        while (m.find()) {
            System.out.println(m.group(1));
            count++;
        }
        System.out.println(count);
    }
}
