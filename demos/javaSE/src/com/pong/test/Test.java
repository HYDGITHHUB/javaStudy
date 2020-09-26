package com.pong.test;

import java.util.concurrent.TimeUnit;

public class Test {
    private static volatile int num = 0;
    public static void main(String[] args) {

        new Thread(()->{
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num ++;
        System.out.println(Thread.currentThread().getName()+num);

//        System.out.println(Test2.one);
    }
}
