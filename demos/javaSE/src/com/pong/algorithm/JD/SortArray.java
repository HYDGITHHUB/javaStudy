package com.pong.algorithm.JD;

import java.util.*;

public class SortArray {

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        int[] nums = new int[n + m];
//        for (int i = 0; i < n; i++) {
//            nums[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < m; i++) {
//            int num = scanner.nextInt();
//            for (int j = 0; j < n + m; ) {
//                while (num > nums[j] && j < n + m) {
//                    j++;
//                }
//            }
//        }
//    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[n+m];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            nums[i+n] = scanner.nextInt();
        }
        Arrays.sort(nums);
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        Integer[] nums2 = new Integer[set.size()];
        Integer[] integers = set.toArray(nums2);
        for (int i = 0; i < integers.length; i++) {
            if (i == 0) System.out.print(integers[i]);
            else System.out.print(" " +integers[i]);
        }
    }
}
