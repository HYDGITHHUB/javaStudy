package com.pong.algorithm.agin;

import java.util.Arrays;
import java.util.Scanner;

public class Games {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long size = scanner.nextLong();
        Long[] nums = new Long[(int) size];
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextLong();
        }
        Arrays.sort(nums);
        System.out.println(nums[(int) (size -1)] + 1);
    }
}
