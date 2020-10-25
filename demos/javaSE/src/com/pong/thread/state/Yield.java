package com.pong.thread.state;

//线程礼让:线程让出当前资源,但是当前线程不转为阻塞状态而是继续为就绪状态,等待CPU调度
public class Yield implements Runnable {
    private int nums = 100;
    private Boolean flag = true;

    @Override
    public void run() {
        while (true) {
            if (nums <= 0) {
                flag = false;
                break;
            }
            if (nums % 10 == 0) Thread.yield();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + nums--);
        }
    }

    public static void main(String[] args) {
        Yield yield =  new Yield();
        new Thread(yield,"小红").start();
        new Thread(yield,"小明").start();
    }
}


//package com.pong.Thread.state;
//
////线程礼让:线程让出当前资源,但是当前线程不转为阻塞状态而是继续为就绪状态,等待CPU调度
//public class Yield implements Runnable {
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName() + "开始了");
//        Thread.yield();
//        System.out.println(Thread.currentThread().getName() + "结束了");
//    }
//
//    public static void main(String[] args) {
//        Yield yield = new Yield();
//        new Thread(yield,"小明").start();
//        new Thread(yield,"小红").start();
//    }
//}
