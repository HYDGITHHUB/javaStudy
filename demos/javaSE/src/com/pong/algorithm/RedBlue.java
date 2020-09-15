package com.pong.algorithm;

import java.io.Serializable;
import java.util.Scanner;
/**
 * 有一个非常经典的概率问题，是一个袋子里面有若干个红球和若干个蓝球，
 * 两个人轮流取出一个球，谁先取到红球谁就赢了，当人的先后顺序和球的数量确定时，
 * 双方的胜率都可以由计算得到，这个问题显然是很简单的。
 * <p>
 * 现在有一个进阶版的问题，同样是一个袋子里面有n个红球和m个蓝球，
 * 共有A，B，C三人参与游戏，三人按照A，B，C的顺序轮流操作，在每一回合中，
 * A，B，C都会随机从袋子里面拿走一个球，然而真正分出胜负的只有A，B两个人，
 * 没错，C就是来捣乱的，他除了可以使得袋子里面减少一个球，没有其他任何意义，
 * 而A，B谁 先拿到红球就可以获得胜利，但是由于C的存在，两人可能都拿不到红球，此时B获得胜利。
 */
public class RedBlue implements Serializable {
    public static void main(String[] args) {
        boolean b;
        System.out.println(b = (0 < 10));
        float fa[];
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            System.out.printf("%.5f", solution(reader.nextInt(), reader.nextInt()));
        }
    }

    private static double solution(int n, int m) {
        double[][] dp = new double[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1.0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] += (double) i / (i + j);
                if (j > 1) {
                    dp[i][j] += (double) j / (i + j) * (j - 1) / (i + j - 1) * i / (i + j - 2) * dp[i - 1][j - 2];
                }
                if (j > 2) {
                    dp[i][j] += (double) j / (i + j) * (j - 1) / (i + j - 1) * (j - 2) / (i + j - 2) * dp[i][j - 3];
                }
            }
        }
        return dp[n][m];
    }
}
