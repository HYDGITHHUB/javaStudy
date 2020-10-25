package com.pong.eightAlgorithm;

/**
 * 冒泡排序:将要排序数组中的大的元素依次排序到最后,就像浮起来一样.从首位开始,和后面以为进行比较,将大的向后挪动,
 * 直到最后一位是最大值,然后下轮循环就不需要比较该位的数字了.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int nums[] = {2, 10, 2, 3, 5, 19};
        bubbleSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    private static void bubbleSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] nums) {

    }
}
