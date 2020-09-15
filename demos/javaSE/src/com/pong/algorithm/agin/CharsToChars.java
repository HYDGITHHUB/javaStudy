package com.pong.algorithm.agin;

import java.util.Scanner;

public class CharsToChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();
        toSub(s,t);
    }
    public static int toSub(String s,String t) {
        if (t.length() > s.length() || t.length() <= 0 || s.length() <= 0) {
            System.out.println(0);
            return 0;
        }
        int size = 0;
        int count = 0;
        int position = 1;
        for (int i = 1; i <= s.length(); i++) {

            if (i > t.length()) {
                while (position <= s.length() - t.length()) {
//                    String test = s.substring(position,i);
                    if (s.substring(position,i).equals(t)) {
                        count++;
                        position ++;
                        break;
                    }
                    position ++;
                    break;
                }
            } else if (s.substring(0, i).equals(t)){
//                String test = s.substring(0,i);
                count++;
            }
            if (size == 0) {
                System.out.print(count);
                size ++;
            }
            else System.out.print(" " + count);
        }
        return 1;
    }
}