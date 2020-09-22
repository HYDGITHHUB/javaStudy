package com.pong.Thread.trouble;

import java.util.concurrent.TimeUnit;

//生产者消费者模式:某个线程依赖的资源来自于另外一个线程
//管程法
public class ProducerConsumer {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tickets.increment();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tickets.decrement();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tickets.increment();
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tickets.decrement();
            }
        },"D").start();
    }
}

class Tickets {
    private int nums = 1;

    //
    public synchronized void increment() {
        while (nums != 1) {
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
        nums--;
        System.out.println(Thread.currentThread().getName() + "消费,剩余" + nums);
        this.notify();
    }

    //
    public synchronized void decrement() {
        while (nums != 0) {
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
        nums++;
        System.out.println(Thread.currentThread().getName() + "生产,剩余" + nums);
        this.notify();
    }
}


//package com.pong.Thread.trouble;
//
////生产者消费者模式:某个线程依赖的资源来自于另外一个线程
////管程法
//public class ProducerConsumer implements Runnable {
//
//    int num = 10;
//
//    public static void main(String[] args) {
//        ProducerConsumer ps = new ProducerConsumer();
//        new Thread(ps).start();
//        new Thread(ps).start();
//        new Thread(ps).start();
//        new Thread(ps).start();
//    }
//
//    //生产者
//    public synchronized void producer() {
//        try {
//            while (true) {
//            if (num >= 10) {
//                System.out.println("等待消费");
//                consumer();
//                this.wait();
//            } else {
//                num++;
//                this.notify();
//                System.out.println("消费一个物品,当前物品共计" + num);
//                Thread.sleep(200);
//            }}
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //消费者
//    public synchronized void consumer() {
//        try {
//            while (true) {
//            if (num <= 0) {
//                System.out.println("等待生产");
//                producer();
//                this.wait();
//            } else {
//                num--;
//                this.notify();
//                System.out.println("消费了一个产品,当前产品共计" + num);
//                Thread.sleep(150);
//            }}
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void controlNum() {
//
//    }
//
//    @Override
//    public  void run() {
//        consumer();
//        producer();
//    }
//}
//
//
