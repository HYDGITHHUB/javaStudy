package com.pong.algorithm.acm;

import java.util.Scanner;

public class BigHeight {
    public static void main(String[] args) {
        //输入
        Scanner scanner = new Scanner(System.in);
        int groups = scanner.nextInt();
        int[][] nums = new int[groups][2];
        for (int i = 0; i < groups; i++) {
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
        }
        //处理
        int result = 0;
        for (int i = 0; i < groups; i++) {
            result = nums[i][0] >= nums[i][1] ? nums[i][0] * 2 + nums[i][1] : nums[i][1] * 2 + nums[i][0];
            System.out.println(result);
        }
    }
}
