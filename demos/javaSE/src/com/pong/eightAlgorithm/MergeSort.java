package com.pong.eightAlgorithm;

/**
 * 归并排序:
 */
public class MergeSort {
    public static void main(String[] args) {
        int nums[] = {2, 10, 2, 3, 5, 19};
        mergeSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    /**
     * 拆分
     *
     * @param nums
     * @param low
     * @param height
     */
    private static void mergeSort(int[] nums, int low, int height) {
        if (low < height) {
            int mid = (low + height) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, height);
            merge(nums, low, mid, height);
        }
    }

    /**
     * 合并
     *
     * @param nums
     * @param low
     * @param mid
     * @param height
     */
    private static void merge(int[] nums, int low, int mid, int height) {
        int[] temp = new int[nums.length];
        int k = 0, i = low, j = mid + 1;
        while (i <= mid && j <= height) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= height) {
            temp[k++] = nums[j++];
        }
        for (int num = 0; num < k; num++) {
            nums[low + num] = temp[num];
        }
    }
}
