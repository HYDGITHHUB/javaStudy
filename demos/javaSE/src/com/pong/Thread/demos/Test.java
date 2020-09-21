package com.pong.Thread.demos;

public class Test implements Runnable {

    private int tickets = 10;

    @Override
    public void run() {
        while (true) {
            if (tickets <= 0) break;
            if (Thread.currentThread().getName().equals("黄牛")) break;
            else {
            System.out.println(Thread.currentThread().getName() + "获得票" + tickets--);}
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        Test test1 = new Test();
        Test test2 = new Test();
        new Thread(test, "学生").start();
        new Thread(test1, "老师").start();
        new Thread(test2, "黄牛").start();
    }
}
