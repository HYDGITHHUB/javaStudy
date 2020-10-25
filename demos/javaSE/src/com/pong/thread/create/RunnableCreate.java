package com.pong.thread.create;

public class RunnableCreate implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            System.out.println("implements Runnable 实现--" + i);
        }
    }

    public static void main(String[] args) {
        RunnableCreate runnableCreate = new RunnableCreate();

        new Thread(runnableCreate).start();

        new Thread(()->{
            for (int i = 0; i < 200; i++) {
                System.out.println("通过拉姆达实现多线程---" + i);
            }
        },"myThread").start();

        for (int i = 0; i < 200; i++) {
            System.out.println("主线程---" + i);
        }
    }
}
