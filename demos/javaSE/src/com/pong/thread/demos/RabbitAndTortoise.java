package com.pong.thread.demos;

public class RabbitAndTortoise implements Runnable {

    private Boolean flag = false;

    @Override
    public void run() {
        for (int i = 0; i <= 200; i++) {
            isFinish(i);
            if (flag) break;
            if (Thread.currentThread().getName().equals("兔子") && i %20 == 0) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    public Boolean isFinish(int steps) {
        if (steps >= 200){
            System.out.println(Thread.currentThread().getName()+"获胜");
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        RabbitAndTortoise rabbitAndTortoise = new RabbitAndTortoise();
        new Thread(rabbitAndTortoise,"乌龟").start();
        new Thread(rabbitAndTortoise,"兔子").start();
    }
}