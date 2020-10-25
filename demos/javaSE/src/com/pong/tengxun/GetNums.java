package com.pong.tengxun;

import java.util.ArrayList;
import java.util.Arrays;

public class GetNums {
    public static void main(String[] args) {
        int[] array = {-1, 2, 3, 4, 5, 6};
        int size = 3;
        int sum = 6;
        int[][] fool = fool(array, size, sum);
        for (int[] ints : fool) {
            for (int anInt : ints) {
                System.out.print(anInt+",");
            }
            System.out.println();
        }
    }

    public static int[][] fool(int[] array, int size, int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        Arrays.sort(array);
        int position = 0;
        for (int i = 0; i < array.length; i++) {
            while (size>0) {
                position+=array[i];
                i++;
                size--;
            }

        }
        return new int[][]{{12,2},{2,4}};
    }
}
