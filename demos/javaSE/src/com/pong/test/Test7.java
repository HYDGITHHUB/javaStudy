package com.pong.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//计数器
public class Test7 {
    public static void main(String[] args) {


//
//        Semaphore semaphore = new Semaphore(3);
//
//        for (int i = 0; i < 6; i++) {
//            new Thread(()->{
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName());
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            }).start();
//        }

//        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
//            System.out.println("你好");
//        });
//
//        for (int i = 0; i < 8; i++) {
//            int finalI = i;
//            new Thread(()->{
//                System.out.println(finalI);
//                try {
//                    cyclicBarrier.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }


//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        for (int i = 0; i < 8; i++) {
//            int finalI = i;
//            new Thread(()->{
//                System.out.println(finalI);
//               countDownLatch.countDown();
//            }).start();
//        }
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
