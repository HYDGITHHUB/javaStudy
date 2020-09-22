package com.pong.Thread.trouble;

import java.util.concurrent.TimeUnit;

public class CyclePrint {
    public static void main(String[] args) {
        Cycle cycle = new Cycle();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                cycle.num();
            }
        },"num").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                cycle.chars();
            }
        },"chars").start();
    }
}

class Cycle {
    //true 输出 字符,false 输出数字
    boolean flag = true;

    int beginNum = 0;
    char beginChars = 'a';

    public synchronized void num() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = !flag;
        System.out.println(Thread.currentThread().getName()+"-->"+beginNum++);
        this.notify();
    }

    public synchronized void chars() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = !flag;
        System.out.println(Thread.currentThread().getName()+"-->"+(char) ((int)beginChars++));
        this.notify();
    }
}