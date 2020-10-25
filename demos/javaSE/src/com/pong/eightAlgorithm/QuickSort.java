package com.pong.eightAlgorithm;

/**
 * 快速排序:冒泡排序的升级版本,将数组的随意一位定义为标志位(一般是首位或者最后一位),然后通过定义的指针,
 * 先向后往前找出比标志位小的,然后再先前往后找出比标志位大的,然后交换即可,循环将数组通过标志位一分为二.
 * 然后通过递归即可将数组进行排序.
 */
public class QuickSort {
    public static void main(String[] args) {
        int nums[] = {2, 10, 2, 3, 5, 19};
        quickSort(nums, 0, nums.length-1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    private static void quickSort(int[] nums, int begin, int end) {
        if (begin>end) return;
        int i, j, temp, position;
        i = begin;
        j = end;
        position = nums[begin];

        while (i < j) {
            while (nums[j] >= position && j > i) j--;
            while (nums[i] <= position && i < j) i++;
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[begin] = nums[i];
        nums[i] = position;
        quickSort(nums,begin,j-1);
        quickSort(nums,j+1,end);
    }
}
