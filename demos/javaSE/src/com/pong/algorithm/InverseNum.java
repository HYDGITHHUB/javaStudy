package com.pong.algorithm;

import java.util.Arrays;

//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
//        输入描述:
//        题目保证输入的数组中没有的相同的数字
//
//        数据范围：
//
//        对于%50的数据,size<=10^4
//
//        对于%75的数据,size<=10^5
//
//        对于%100的数据,size<=2*10^5
//
//        示例1
//        输入
//        1,2,3,4,5,6,7,0
//        输出
//        7
public class InverseNum {


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 0};
        InverseNum inverseNum = new InverseNum();
        System.out.println(inverseNum.InversePairs(nums));
    }

    private int num = 0;

    public int InversePairs(int[] array) {
        int[] result = getSortArray(array);
        return num;
    }

    public int[] getSortArray(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] right = Arrays.copyOfRange(array, array.length / 2, array.length);
        left = getSortArray(left);
        right = getSortArray(right);
        //下面计算两个有序数组之间的逆序，并且合并成一个有序数组
        int i = 0, j = 0;
        int[] tmparr = new int[array.length];
        while (i < left.length && j < right.length) {
            if (left[i] > right[j]) {
                num = (num + left.length - i) % 1000000007;
                tmparr[i + j] = right[j];
                j++;
            } else {
                tmparr[i + j] = left[i];
                i++;
            }
        }
        if (i == left.length) {
            for (int m = j; m < right.length; m++) {
                tmparr[i + m] = right[m];
            }
        }
        if (j == right.length) {
            for (int n = i; n < left.length; n++) {
                tmparr[j + n] = left[n];
            }
        }
        return tmparr;


    }
}


//    public static void main(String[] args) {
//        int[] nums = {1,2,3,4,5,6,7,0};
//        System.out.println(InversePairs(nums));
//    }
//    public static int InversePairs(int [] array) {
//        if (array.length <= 1) return 0;
//        long count = 0l;
//        for (int i = 1; i < array.length; i ++) {
//            for (int j = 0; j < i; j ++) {
//                if (array[i] < array[j]) count++;
//            }
//        }
//        return (int)count % 1000000007;
//    }
//}
