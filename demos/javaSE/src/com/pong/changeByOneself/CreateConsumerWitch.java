package com.pong.changeByOneself;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateConsumerWitch {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.a();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.b();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.c();
            }
        },"C").start();
    }
}

class Data3 {
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void a() {
        lock.lock();
        try {
            while (num!=0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num = 1;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void b() {
        lock.lock();
        try {
            while (num!=1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num = 2;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void c() {
        lock.lock();
        try {
            while (num!=2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num = 0;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}