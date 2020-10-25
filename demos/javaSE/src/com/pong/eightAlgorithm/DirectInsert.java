package com.pong.eightAlgorithm;

/**
 * 直接插入法:通过当前位置的元素与前面的元素进行比较,如果前面的元素大于当前元素,则双方交换位置
 * 默认为第一个元素之排好序的
 * 稳定的排序方法
 */
public class DirectInsert {

    public static void directInsert(int[] nums) {
        int length = nums.length;
        int temp = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[j] < nums[j-1]) {
                    temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int nums[] = {2,10,2,3,5,19};
        directInsert(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
