package com.pong.algorithm.wps;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int[][] nums = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        int count = getCount(row, col, nums);
        System.out.println(count);
    }

    public static int getCount(int rows, int cols, int[][] nums) {
        int row = rows;
        int col = cols;
        int beginRow = 0;
        int beginCol = 0;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (nums[i][j] == 1) {
                    if ((i - 1 >= 0 && nums[i - 1][j] == 1) || (j + 1 < col && nums[i][j + 1] == 1) ||
                            (i + 1 < row && nums[i + 1][j] == 1) || (j - 1 >= 0 && nums[i][j - 1] == 1)) {
                    } else {
                        count++;
                    }
                    while (j + 1 < col && nums[i][j + 1] == 1) j++;
                }
            }
        }

        return count;
    }
}
//5 5
//1 1 1 0 1
//1 1 0 1 0
//0 1 0 1 0
//1 1 1 0 0
//0 0 0 0 1