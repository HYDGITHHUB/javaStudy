package com.pong.changeByOneself;

public class BubbleSort {
    public static void main(String[] args) {
        int nums[] = {2, 10, 2, 3, 5, 19};
        bubbleSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
    public static void bubbleSort(int[] array) {
        int length = array.length;
        int temp;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
