package com.pong.algorithm;

import java.util.Scanner;

/**
 * 春天是鲜花的季节，水仙花就是其中最迷人的代表，
 * 数学上有个水仙花数，他是这样定义的： “水仙花数”是指一个三位数，
 * 它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3。
 * 现在要求输出所有在m和n范围内的水仙花数。
 */

public class ShuiXian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int ones,tens,hundreds;
            int counts = 0;
            for (int i = m; i <= n ; i++) {
                ones = i % 10;
                tens = (i/10) % 10;
                hundreds = i / 100;
                if ((int)(Math.pow(ones,3) + Math.pow(tens,3) + Math.pow(hundreds,3)) == i) {
                    if (counts > 0) System.out.print(" " + i);
                    else System.out.print(i);
                    counts ++;
                }
            }
            if (counts == 0) System.out.println("no");
        }
    }
}
