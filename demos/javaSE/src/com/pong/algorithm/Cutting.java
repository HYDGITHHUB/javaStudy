package com.pong.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 有一个x*y*z的立方体，要在这个立方体上砍k刀，
 * 每一刀可以看作是用一个平行于立方体某一面的平面切割立方体，
 * 且必须在坐标为整数的位置切割，如在x=0.5处用平面切割是非法的。
 *
 * 问在切割k刀之后，最多可以把立方体切割成多少块。
 */
public class Cutting {

    private long a = 1;
    private long b = 1;
    private long c = 1;


    // 排序解决方案：
    public long func(int x, int y, int z, int k) {
        int arry[] = {x, y, z};
        Arrays.sort(arry);
        //System.out.println(arry);
        int size = k + 1;
        int i;
        for (i = 0; i < k; ) {


            if (a < arry[0] && a == b) {
                a++;
                i++;
            } else if (c < arry[1] && c == b) {
                c++;
                i++;
            } else if (b < arry[2]) {
                b++;
                i++;
            } else break;
        }
        return a * b * c;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cutting main = new Cutting();
        while (in.hasNextInt()) {//注意while处理多个case              int a = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            int k = in.nextInt();
            System.out.println(main.func(x, y, z, k));
        }

    }
}
