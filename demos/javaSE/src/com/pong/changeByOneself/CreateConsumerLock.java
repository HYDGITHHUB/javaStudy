package com.pong.changeByOneself;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateConsumerLock {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "B").start();
    }
}

class Data2 {
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + ":" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + ":" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
