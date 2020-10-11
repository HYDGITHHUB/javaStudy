package com.pong.algorithm.t58;

public class Main {

    public static void main(String[] args) {
        System.out.println(lineup("GGBBG"));
    }


    public static int lineup(String peoples) {
        // write code here
        int countG = 0;
        int countB = 0;
        int length = peoples.length();
        if (length <= 0) return 0;
        if (length == 1) return 0;

        char[] charsG = peoples.toCharArray();
        char[] charsB = peoples.toCharArray();
        //考虑移动G
        for (int i = length - 1; i >= 0; i--) {
            while (i > 0 && charsG[i] == 'G') {
                length--;
                i--;
            }
            break;
        }
        for (int i = 0; i < charsG.length; i++) {
            if (charsG[i] == 'G') {
                countG += (length-i+1);
            }
        }
        //考虑移动B
        length = peoples.length();
        for (int i = length - 1; i >= 0; i--) {
            while (i > 0 && charsB[i] == 'B') {
                length--;
                i--;
            }
            break;
        }
        for (int i = 0; i < charsB.length; i++) {
            if (charsB[i] == 'B') {
                countB += (length-i+1);
            }
        }
        int count = countB < countG ? countB : countG;
        return count;
    }


}
