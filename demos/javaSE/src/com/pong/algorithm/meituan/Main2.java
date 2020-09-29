package com.pong.algorithm.meituan;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        String str = scanner.next();
        if (length <= 0 || length != str.length()) {
            System.out.println(0);
            return;
        }
        int max = 0;
        int de = 0;
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == 'E') {
                max++;
            } else if (str.charAt(i) == 'F') {
                de++;
            }
            result = (max-de) > result ? (max-de) : result;
        }
        System.out.println(result);
    }
}
//小美喜欢字母E，讨厌字母F。在小美生日时，小团送了小美一个仅包含字母E和F的字符串，小美想从中选出一个包含字母E数量与字母F数量之差最大的子串。
//
//*子串：从字符串前面连续删去若干个字符，从后面连续删去若干个字符剩下的字符串（也可以一个都不删），例如abcab是fabcab的子串，而不是abcad的子串。我们将空串看作所有字符串的子串。
//
//
//
//输入描述
//第一行一个正整数 n表示字符串的长度。
//
//第二行长度为n，且仅包含大写字母’E’,’F’的字符串（不含引号）
//
//n<=300000
//
//输出描述
//输出一个整数，表示最大的差值
//
//
//样例输入
//5
//EFEEF
//样例输出
//2