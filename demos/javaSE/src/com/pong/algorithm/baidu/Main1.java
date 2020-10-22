package com.pong.algorithm.baidu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    public static void main(String[] args) {
        //接收
        Scanner scanner = new Scanner(System.in);
        int peopleNum = scanner.nextInt();
        int teamNum = scanner.nextInt();
        int num = scanner.nextInt();
        int[][] teams = new int[teamNum][2];
        for (int i = 0; i < teams.length; i++) {
            for (int j = 0; j < teams[i].length; j++) {
                teams[i][j] = scanner.nextInt();
            }
        }
        //处理
        ArrayList<Integer> list = getRanking(peopleNum, teamNum, num, teams);
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i != nums.length - 1) System.out.print(" ");
        }
    }

    public static ArrayList<Integer> getRanking(int peopleNum, int teamNum, int num, int[][] teams) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < teams.length; i++) {
            if (stack.empty()) {
                stack.add(teams[i][0]);
                stack.add(teams[i][1]);
            } else {
                if (stack.peek() == teams[i][0]) {
                    stack.add(teams[i][1]);
                } else if (stack.peek() == teams[i][1]) {
                    stack.pop();
                    stack.add(teams[i][0]);
                    stack.add(teams[i][1]);
                } else {
                    stack.add(teams[i][0]);
                    stack.add(teams[i][1]);
                }
            }
        }
        int pop = 0;
        int next = 0;
        int count = 1;
        while (!stack.isEmpty()) {
            pop = stack.peek();
            stack.pop();
            if (!stack.isEmpty()) next = stack.peek();
            if (next < pop) {
                list.add(count);
                count++;
            }
        }
        return list;
    }
}
