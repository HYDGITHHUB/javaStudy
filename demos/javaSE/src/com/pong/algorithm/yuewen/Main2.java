package com.pong.algorithm.yuewen;

public class Main2 {
    public static int countBit (int n) {
        // write code here
        int rem = 0;
        int ch = 0;
        int i = 0;
        int count = 0;
        while (n != 0) {
            rem = n % 2;
            n = n / 2;
            ch += rem * (Math.pow(10,i));
            i ++;
        }
        String string = String.valueOf(ch);
        for (int j = 0; j < string.length(); j++) {
            if (string.charAt(j) == '1') count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countBit(15));
        System.out.println(countBit(1));
    }
}
