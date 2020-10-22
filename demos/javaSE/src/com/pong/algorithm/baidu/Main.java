package com.pong.algorithm.baidu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //接收
        Scanner scanner = new Scanner(System.in);
        int step = scanner.nextInt();
        int[] nums = new int[step];
        String eachNum = scanner.next();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) eachNum.charAt(i) - 48;
        }
        //处理
        System.out.println(getStep(step, nums));
    }

    public static int getStep(int step, int[] nums) {
        int countStep = 0;
        if (step == 1) return 0;
        int position = 0;
        for (int i = 0; i < nums.length; ) {
            position = i;
            for (int j = step - 1; j >= 0; j--) {
                if (nums[i] == nums[j]) {
                    i = j + 1;
                    countStep++;
                    j = step - 1;
                    break;
                }
            }
            if (i == position) {
                i += 1;
            }
            if (countStep >= step - 1) break;
        }
        return countStep;
    }
}
