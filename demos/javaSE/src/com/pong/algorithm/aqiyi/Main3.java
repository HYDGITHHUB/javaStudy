package com.pong.algorithm.aqiyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        String[] strings = nums.split(" ");
        int[] num = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            num[i] = Integer.parseInt(strings[i]);
        }
        List<List<Integer>> lists = new ArrayList<>();
        lists = result(num);
        for (List<Integer> list : lists) {
            System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
        }
    }
    public static List<List<Integer>> result(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = 0 - nums[i];
            int l = i + 1;
            int r = length - 1;
            while (l < r) {
                List<Integer> list = new ArrayList<>();
                if (nums[l] + nums[r] == target) {
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    lists.add(list);
                    while (r > l && nums[l + 1] == nums[l]) l++;
                    while (r > l && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (nums[l] + nums[r] > target) r--;
                else l++;
            }
        }
        return lists;
    }
}
