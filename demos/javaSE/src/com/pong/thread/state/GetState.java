package com.pong.thread.state;

// getState 获取当前线程的六种状态
// State.XXX 枚举类型,可以与当前常量进行比较,从而根据不同的状态有不同的操作
public class GetState implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GetState state = new GetState();
        Thread thread = new Thread(state);
        thread.start();
        System.out.println(thread.getState());
        try {
            thread.sleep(20);
            System.out.println(thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (thread.getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getState());
        }



    }
}
