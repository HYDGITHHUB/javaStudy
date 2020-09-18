package com.pong.algorithm.JD;

import java.util.ArrayList;
import java.util.Scanner;
//尽管是一个CS专业的学生，小B的数学基础很好并对数值计算有着特别的兴趣，喜欢用计算机程序来解决数学问题，现在，她正在玩一个数值变换的游戏。她发现计算机中经常用不同的进制表示一个数，如十进制数123表达为16进制时只包含两位数7、11（B），用八进制表示为三位数1、7、3，按不同进制表达时，各个位数的和也不同，如上述例子中十六进制和八进制中各位数的和分别是18和11,。 小B感兴趣的是，一个数A如果按2到A-1进制表达时，各个位数之和的均值是多少？她希望你能帮她解决这个问题？ 所有的计算均基于十进制进行，结果也用十进制表示为不可约简的分数形式。
//
//        输入描述:
//        输入中有多组测试数据，每组测试数据为一个整数A(1 ≤ A ≤ 5000).
//
//        输出描述:
//        对每组测试数据，在单独的行中以X/Y的形式输出结果。
//
//        输入例子1:
//        5
//        3
//
//        输出例子1:
//        7/3
//        2/1

public class TenToEach {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        int temp;
        int simplification;
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            temp = num;
            for (int i = 2; i < num; i++) {
                while (num != 0) {
                    list.add(num % i);
                    num = num / i;
                }
                num = temp;
            }
            for (int i = 0; i < list.size(); i++) {
                count += list.get(i);
            }
            int base = temp - 2;
            simplification = 2;
            while (simplification < 10) {
                while ((count % simplification == 0) && (base % simplification == 0)) {
                    count /= simplification;
                    base /= simplification;
                }
                if (base == 1) break;
                simplification++;
            }
            System.out.println(count + "/" + base);
            list.clear();
            count = 0;
        }
    }
}
