package com.pong.algorithm.aqiyi;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        String[] strings = nums.split(" ");
        Long[] num = new Long[strings.length];
        for (int i = 0; i < strings.length; i++) {
            num[i] = Long.valueOf(Integer.parseInt(strings[i]));
        }
        System.out.println(result(num));
    }
    public static Long result(Long[] num) {
        if (num.length == 1) return num[0];
        long count = 0;
        long result = 0;
        for (int i = 0; i < num.length; i++) {
            for (int j = 1; j < num.length; j++) {
                if (num[i] == num[j]) {
                    count++;
                }
            }
            if (count > (num.length/2)) result = num[i];
        }
        return result;
    }
}
