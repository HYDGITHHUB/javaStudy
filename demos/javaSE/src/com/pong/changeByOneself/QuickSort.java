package com.pong.changeByOneself;

public class QuickSort {
    public static void main(String[] args) {
        int nums[] = {2, 10, 2, 3, 5, 19};
        quickSort(nums, 0, nums.length-1);
        for (int num : nums) {
            System.out.println(num);
        }
    }
    public static void quickSort(int[] array,int low,int height) {
        if (low > height) return;
        int i = low;
        int j = height;
        int position = array[low];
        int temp = 0;
        while (i < j) {
            while (array[j] >= position && j > i) j--;
            while (array[i] <= position && i < j) i++;
            if (i < j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        array[low] = array[i];
        array[i] = position;
        quickSort(array,low,j-1);
        quickSort(array,j + 1, height);
    }
}
