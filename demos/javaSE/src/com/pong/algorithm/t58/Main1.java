package com.pong.algorithm.t58;

import java.util.ArrayList;

public class Main1 {
    public static void main(String[] args) {
        int[] nums = removeDuplicate(new int[]{3,5,8,2,3,8});
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static int[] removeDuplicate (int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        int isFirst = 0;
        int position = -1;
        for (int i : array) {
            if (isFirst == 0) {
                list.add(i);
                isFirst++;
            } else {
                if (list.contains(i)) {
                    for (int i1 = 0; i1 < list.size(); i1++) {
                        if (list.get(i1) == i) {
                            position = i1;
                            break;
                        }
                    }
                    list.remove(position);
                }
                list.add(i);
            }
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
}
