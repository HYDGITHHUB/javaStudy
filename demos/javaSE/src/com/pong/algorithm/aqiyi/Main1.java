package com.pong.algorithm.aqiyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * 输入描述
 * 一个字符串，里面的字符可以有重复
 * <p>
 * 输出描述
 * 输出为一个数字，表示最长不重复字符串的长度
 * <p>
 * 样例输入
 * abcdab
 * 样例输出
 * 4
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(result(str));
    }

    public static int result(String str) {
        if (str.length() <= 0) return 0;
        if (str.length() == 1) return 1;
        Set<String> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            set.add(str.substring(i, i + 1));
            if (set.size() < i) {
                max = max > set.size()?max:set.size();
            }
        }
        return max;
    }
}
