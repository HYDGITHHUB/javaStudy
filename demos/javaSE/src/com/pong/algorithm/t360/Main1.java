package com.pong.algorithm.t360;


//搬家公司正在帮助一家人将小物体装箱。一个箱子的大小是有限的，公司可以把一个箱子分成最多k个独立的隔间，将一个箱子分成r个隔间
//需要r-1个隔板（这一个箱子没有放隔板也拥有一个本身的隔间）。而这一次搬家工作只携带了b个隔板
//在每一个隔间中，由于物件放多了容易损坏，最多只能放v个物体。现在这家人有a个物体，请问最少需要多少个箱子，才能将所有的物体装箱？

import java.util.Scanner;

//10 3 2 1
//10 3 2 2
//样例输出
//7
//3
//a b k v
//对于样例1，第1,2,3个箱子分成两个隔间，使用掉了3个隔板，装了6个物件。第4,5,6,7个箱子没有使用隔板，装了4个物件。共7个箱子装完了所有物件。
//对于样例2，第1,2个箱子分成两个隔间，使用掉了2个隔板，装了8个物件，最后两个物件装在第三个箱子中。

// goods:多少个物品 spilt:带了多少个隔板 maxSpilt:每个箱子最多多少个隔间 maxSize:每个隔间最多放多少物品
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int goods = scanner.nextInt();
            int spilt = scanner.nextInt();
            int maxSpilt = scanner.nextInt();
            int maxSize = scanner.nextInt();
            Main1 main = new Main1();
            int num = main.getBoxes(goods, spilt, maxSpilt, maxSize);
            System.out.println(num);
        }
    }

    public int getBoxes(int goods, int spilt, int maxSpilt, int maxSize) {
        if (goods <= 0) return 0;
        int count = 0;
        //到达最大隔间所需要的木板
        int contain = maxSpilt - 1;
        //带的木板可以划分多少个这样的箱子
        int size = 0;
        int last = 0;
        if (contain > 0) {
            size = spilt / contain;
            last = spilt % contain;
        } else {
            size = spilt;
        }
        int num = 0;
// goods:多少个物品 spilt:带了多少个隔板 maxSpilt:每个箱子最多少个隔间 maxSize:每个隔间最多放多少物品
        while (count < goods) {
            if (size > 0) {
                count += maxSize * maxSpilt;
                size--;
                num++;
            } else if (size == 0 && last > 0) {
                count += (last+1)*maxSize;
                size--;
                num++;
            } else {
                count += maxSize;
                num++;
            }
        }
        return num;
    }
}
