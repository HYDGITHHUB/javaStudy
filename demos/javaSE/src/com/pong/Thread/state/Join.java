package com.pong.Thread.state;

//线程抢占:通过 join 方法,使用的线程即可抢占当前CPU资源进行执行,知道执行完退出
public class Join implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+i);
        }
    }

    public static void main(String[] args) {
        Join join = new Join();
        Thread thread = new Thread(join);
        thread.start();
        for (int i = 0; i < 500; i++) {
            try {
                if (i == 10) thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}
