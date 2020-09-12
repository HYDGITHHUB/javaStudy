package com.pong.algorithm;

import java.util.Scanner;

/**
 * 人脑对于长度特别长的字符串的处理速度是有限的，
 * 但是最强大脑挑战的就是人脑的极限，现在有这样一项挑战，
 * 给出一个很长的字符串S，和一个较短的字符串T，请你求出对于每一个前缀[1,r]内有多少个T字符串。
 */
public class CharsToChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int len1 = str1.length();
        int len2 = str2.length();
        int[] res = new int[len1];

        int temp = 0;
        for (int i = 0; i < len2 - 1; i++) {
            res[i] = 0;
        }
        for (int i = len2 - 1; i < len1; i++) {
            temp = Math.max(res[i - 1], res[i - len2 + 1]);
            if (isSame(str1, str2, i - len2 + 1, i)) {
                temp++;
            }
            res[i] = temp;
        }
        for (int i = 0; i < len1; i++) {
            System.out.print(res[i] + " ");
        }

        sc.close();
    }

    private static boolean isSame(String str1, String str2, int i, int i2) {
        int num = 0;
        for (int j = i; j <= i2; j++) {
            if (str1.charAt(j) != str2.charAt(num++)) {
                return false;
            }
        }

        // TODO Auto-generated method stub
        return true;
    }
}
