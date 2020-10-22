package com.pong.algorithm.yuewen;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.nextLine();
        String[] str = new String[lines];
        for (int i = 0; i < lines; i++) {
            str[i] = scanner.nextLine();
        }
        //排序依据:2431
        for (String s : str) {
            String[] split = s.split(",");

        }
    }
}
