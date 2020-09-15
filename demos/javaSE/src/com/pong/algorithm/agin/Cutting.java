package com.pong.algorithm.agin;

import java.util.Scanner;

public class Cutting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long x = scanner.nextInt();
        long y = scanner.nextInt();
        long z = scanner.nextInt();
        long cuts = scanner.nextInt();
        System.out.println(getNum(x, y, z, cuts));
    }

    public static long getNum(long x, long y, long z, long cuts) {
        if (cuts <= 1) return 1;
        if (x <= 1 || y <= 1 || z <= 1) return 1;
        int i = 1, j = 1, k = 1;
        while (cuts > 0 && (i + j + k) < (x + y + z)) {
            if (i < x && cuts > 0) {
                cuts--;
                i++;
            }
            if (j < y && cuts > 0) {
                cuts--;
                j++;
            }
            if (k < z && cuts > 0) {
                cuts--;
                k++;
            }
        }
        return i * j * k;
    }
}
