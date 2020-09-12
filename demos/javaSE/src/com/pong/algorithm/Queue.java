package com.pong.algorithm;

import java.util.Scanner;

/**
 * 给定一个大小为N-1且只包含0和1的序列A1到AN-1，
 * 如果一个1到N的排列P1到PN满足对于1≤i<N，
 * 当Ai=0时Pi<Pi+1，当Ai=1时Pi>Pi+1，
 * 则称该排列符合要求，那么有多少个符合要求的排列？
 */
public class Queue {
    public static void main(String[] args) {
        int md = 10000007;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] m = new int[n];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n - 1; i++) {
            m[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (m[i - 1] == 1) {
                for (int j = n - i - 1; j >= 0; j--) {
                    dp[i][j] += (dp[i - 1][j + 1] + dp[i][j + 1]) % md;
                    dp[i][j] %= md;
                }
            } else {
                dp[i][0] += dp[i - 1][0];
                for (int j = 1; j < n - i; j++) {
                    dp[i][j] += (dp[i - 1][j] + dp[i][j - 1]) % md;
                    dp[i][j] %= md;
                }
            }
        }
        System.out.println(dp[n - 1][0]);
        sc.close();
    }
}
