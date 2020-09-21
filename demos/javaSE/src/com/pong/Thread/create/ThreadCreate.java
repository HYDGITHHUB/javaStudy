package com.pong.Thread.create;

public class ThreadCreate extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("run线程---" + i);
        }
    }

    public static void main(String[] args) {

        ThreadCreate threadCreate = new ThreadCreate();
        threadCreate.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("main线程---"+ i);
        }
    }
}
