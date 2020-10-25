package com.pong.thread.state;
//守护线程:在后台默默执行,当所有的线程停止的时候 JVM虚拟机销毁,此时不考虑守护进程有没有在执行
public class Daemon {
    public static void main(String[] args) {
        Master master = new Master();
        People people = new People();
        Thread thread = new Thread(master);
        Thread thread1 = new Thread(people);
        thread.setDaemon(true);
        thread.start();
        thread1.start();
    }
}

class Master implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("守护");
        }
    }
}

class People implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(10);
                System.out.println("在人间");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

