package com.pong.algorithm.eightSort;

//直接插入
//1.基本思路：
//在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排好顺序的，
// 现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
//
//2.代码实现：
//（1）首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，从第二个数字开始插入，
// 排序好。再依次插入第三个。。。
//（2）设定插入数和得到已经排好序列的最后一个数的位数。temp和j=i-1。
//（3）从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位。
//（4）将当前数放置到空着的位置，即j+1。
public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = {2,4,6,1,4,7,8};
        insertSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void insertSort(int[] nums) {
        int temp;
        for (int i = 1; i < nums.length; i++) {
            temp = nums[i];
            int j;
            for (j = i - 1;j >= 0; j--) {
                if (nums[j] > temp) {
                    nums[j+1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j] = temp;
        }
    }
}
