package com.pong.algorithm.t360;

import java.util.Scanner;

//一只乌龟来到了图论森林游玩。图论森林可以被抽象地看作有n个点m条带权无向边的简单图，没有自环没有重边。
// 乌龟需要从s点到达t点。森林里居住了很多兔子，乌龟在经过一条边时会因为爬得太慢而受到边上兔子们的嘲笑，一条边上兔子数量为这条边的边权。
//乌龟想承受尽可能少的嘲笑，它想找到一条从起点到终点的路径，路径上有一条边的兔子是这条路径所有边中最多的，它想让这个值尽可能少，请问最少是多少。
//第一行四个数n,m,s,t。
//接下来m行，每行三个数u,v,w，表示u和v之间有一条边权为w的无向边。
//输入保证s点和t点连通。
//1≤n≤100,000；0≤m≤200,000；1≤w≤1,000,000,000
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //第一行四个数n,m,s,t。
        //接下来m行，每行三个数u,v,w，表示u和v之间有一条边权为w的无向边。
        //接收数据
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        int[][] nums = new int[3][m];
        for (int i = 0; i < m; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                nums[i1][i] = scanner.nextInt();
            }
        }
        //处理数据
        Main2 main = new Main2();
        int result = main.getNum(n, m, s, t, nums);
        System.out.println(result);
    }

    public int getNum(int n, int m, int s, int t, int[][] nums) {
        int count = 0;
        int begin = s;
        int last = 0;
        int end = t;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i][0] == begin) {
                last = nums[i][1];
                count += nums[i][2];
                if (last == end) break;
            }
        }
        return count;
    }

}

//5 6 1 5
//1 5 100
//1 2 10
//2 5 5
//1 3 3
//3 4 2
//4 5 1
//样例输出
//3
//
//提示
//输入样例2：
//3 2 1 3
//1 2 99
//2 3 99
//1 3 100
//输出样例2：
//99
//样例解释：
//对于样例，简单来看乌龟有1-5，1-2-5，1-3-4-5三个方案可以选择。三个方案中最大权值分别为100，10，3，所以最终乌龟选择了第三条路1-3-4-5。