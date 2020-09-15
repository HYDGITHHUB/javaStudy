package com.pong.algorithm.agin;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DNA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(result(string));
    }

    public static int result(String str) {
        Set<String> set = new HashSet<>();
        int position = 1;
        int size = 4;
        int begin = 0;
        while (position < str.length()) {
            while (begin + position <= str.length()) {
                set.add(str.substring(begin, begin + position));
                if (set.size() >= size) break;
                begin++;
            }
            if (set.size() < size) break;
            size *= 4;
            position++;
        }
        return position;
    }
}
