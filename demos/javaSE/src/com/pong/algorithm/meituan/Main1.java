package com.pong.algorithm.meituan;

//服装店新进了a条领带，b条裤子，c个帽子，d件衬衫，现在要把这些搭配起来售卖。
// 有三种搭配方式，一条领带和一件衬衫，一条裤子和一件衬衫，一个帽子和一件衬衫。
// 卖出一套领带加衬衫可以得到e元，卖出一套裤子加衬衫可以得到f元，卖出一套帽子加衬衫可以得到g元。现在你需要输出最大的获利方式。

import java.util.Arrays;
import java.util.Scanner;

//一行7个整数，空格隔开，分别表示a,b,c,d,e,f,g。
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        int e = scanner.nextInt();
        int f = scanner.nextInt();
        int g = scanner.nextInt();
        Main1 main1 = new Main1();
        int max = main1.getMax(a, b, c, d, e, f, g);
        System.out.println(max);
    }

    public int getMax(int a, int b, int c, int d, int e, int f, int g) {
        int max = 0;
        int min = 0;
        //a d 获利最高(e)
        if (e >= f && e >= g) {
            if (f >= g) {
                min = a < d ? a : d;
                max += e * min;
                d -= min;
                min = b < d ? b : d;
                max += f * min;
                d -= min;
                min = c < d ? c : d;
                max += g * min;
                d -= min;
            } else {
                min = a < d ? a : d;
                max += e * min;
                d -= min;
                min = c < d ? c : d;
                max += g * min;
                d -= min;
                min = b < d ? b : d;
                max += f * min;
                d -= min;
            }
        } else if (f >= e && f >= g) {
            //b d 获利最高(f)
            if (e >= g) {
                min = b < d ? b : d;
                max += f * min;
                d -= min;
                min = a < d ? a : d;
                max += e * min;
                d -= min;
                min = c < d ? c : d;
                max += g * min;
                d -= min;
            } else {
                min = b < d ? b : d;
                max += f * min;
                d -= min;
                min = c < d ? c : d;
                max += g * min;
                d -= min;
                min = a < d ? a : d;
                max += e * min;
                d -= min;
            }
        } else if (g >= e && g >= f) {
            //c d 获利最高(g)
            if (e >= f) {
                min = c < d ? c : d;
                max += g * min;
                d -= min;
                min = a < d ? a : d;
                max += e * min;
                d -= min;
                min = b < d ? b : d;
                max += f * min;
                d -= min;
            } else {
                min = c < d ? c : d;
                max += g * min;
                d -= min;
                min = b < d ? b : d;
                max += f * min;
                d -= min;
                min = a < d ? a : d;
                max += e * min;
                d -= min;
            }
        }
        return max;
    }
}
//样例输入
//2 3 4 5 6 7 8
//样例输出
//39