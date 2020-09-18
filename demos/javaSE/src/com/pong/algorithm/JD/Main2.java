package com.pong.algorithm.JD;
//在一个n行m列的二维地图中，王子的位置为(x1,y1)，公主的位置为(x2,y2)。
//
//        在地图中设有一些障碍物，王子只能朝上、下、左、右四个方向行走，且不允许走出地图也不允许穿越障碍物。
//
//        请编写一个程序判断王子是否可以顺利走到公主所在的位置。

import java.util.Scanner;

//        输入描述
//        多组输入，第1行输入一个正整数T表示输入数据的组数。
//
//        对于每一组输入数据：输入n+1行。
//
//        其中，第1行输入两个正整数n和m表示地图的大小，n为行数，m为列数。（n<=100,m<=100）
//
//        接下来n行表示地图，每一行都有m个字符，其中S表示王子的位置，E表示公主的位置，'.'表示可以通行，'#'表示障碍物（不能通行）。
//
//        输出描述
//        针对每一组输入数据，判断王子是否能够到达公主所在位置？如果可以输出“YES”，否则输出“NO”。
//
//
//        样例输入
//        2
//        2 2
//        .E
//        S.
//        2 2
//        #E
//        S#
//        样例输出
//        YES
//        NO
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            String[][] nums;
            int positionx = 0;
            int positiony = 0;
            int n = 0;
            int m = 0;
            for (int i = 0; i < num; i++) {
                n = scanner.nextInt();//行数
                m = scanner.nextInt();//列数
                nums = new String[n][m];
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        nums[j][k] = scanner.next();
                        if (nums[j][k] == "E") {
                            positionx = j;
                            positiony = k;
                        }
                    }
                }
            }
            System.out.println("NO");
//        while (true) {
//            while (positionx>=0 && positionx<=n && positiony>=0 && positiony<=m) {
//                if (nums[positionx - 1][positiony] == ".") positionx--;
//            }
//        }
        }
    }
}
