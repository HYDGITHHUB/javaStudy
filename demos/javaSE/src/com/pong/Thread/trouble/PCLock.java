package com.pong.Thread.trouble;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//用 Lock 实现生产者消费者问题以及指定下一个线程是哪个
public class PCLock {
    public static void main(String[] args) {
        Foods foods = new Foods();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                foods.dog();
            }, "dog").start();
            new Thread(() -> {
                foods.cat();
            }, "cat").start();
            new Thread(() -> {
                foods.bird();
            }, "bird").start();
        }
    }
}

class Foods {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    public void dog() {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            num++;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cat() {
        lock.lock();
        try {
            while (num != 2) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            num++;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void bird() {
        lock.lock();
        try {
            while (num != 3) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            num = 1;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
