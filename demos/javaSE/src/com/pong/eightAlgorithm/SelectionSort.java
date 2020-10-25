package com.pong.eightAlgorithm;

/**
 * 简单选择排序:从初始位置开始,将为排序的数组中的最小值取出,放到为排序的首位,然后++,进行后面未排序的数组的排序
 * 认为第一个元素为数组中最小的元素,依次同后面的元素进行比较
 */
public class SelectionSort {
    public static void main(String[] args) {
        int nums[] = {2, 10, 2, 3, 5, 19};
        selectionSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    private static void selectionSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[min]) min = j;
            }
            if (min != i) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }
}
