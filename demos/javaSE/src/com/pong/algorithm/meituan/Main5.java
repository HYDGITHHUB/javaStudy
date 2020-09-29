package com.pong.algorithm.meituan;

import java.util.ArrayList;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        for (int k = 0; k < size; k++) {
            int cityNum = scanner.nextInt();
            int[][] eachLength = new int[cityNum - 1][3];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < eachLength.length; j++) {
                    eachLength[j][0] = scanner.nextInt();
                    eachLength[j][1] = scanner.nextInt();
                    eachLength[j][2] = scanner.nextInt();
                }
            }
            Main5 main5 = new Main5();
            int city = main5.getCity(eachLength);
            int minLength = main5.getMinLength(city, eachLength);
            System.out.println(minLength);
        }
    }
    //获取末端城市
    public int getCity(int[][] city) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < city.length; i++) {
            list.add(city[i][0]);
            list.add(city[i][1]);
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i-1)) {
                i += 2;
            } else return list.get(i);
        }
        return list.get(0);
    }

    //获取最短距离
    public int getMinLength(int city, int[][] cities) {
        return 0;
    }
}
//小美和小团计划去邻国旅行。邻国有N座城市，城市间由N-1条双向道路连通，且保证从任意城市出发，沿着这些道路可以到达其它任意城市。小美和小团可以从任意城市出发，沿着城市间的道路，以任意顺序游览每座城市，并且可以在任意城市结束旅行。那么，小美和小团想知道，他们至少要在道路上行驶多少距离，才能保证游历每座城市。
//
//
//
//输入描述
//第一行输入一个整数T（1<=T<=10），表示数据组数。
//
//对于每组数据，第一行输入一个整数N（1<=N<=10^5）；
//
//接下来N-1行，每行输入三个由空格隔开的整数X、Y和L（1<=X,Y<=N、1<=L<=10^4），表示第X座城市和第Y座城市间有一条长为L的双向道路。
//
//输出描述
//每组数据输出占一行，输出一个整数，表示游历每座城市至少要在道路上行驶的距离。
//
//
//样例输入
//1
//5
//1 2 1
//1 3 4
//3 4 2
//3 5 3
//样例输出
//12