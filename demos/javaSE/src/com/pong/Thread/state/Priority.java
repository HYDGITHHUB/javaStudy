package com.pong.Thread.state;

//JVM的线程调度器根据线程的优先级调度线程,但线程的优先级不代表绝对的优先级
public class Priority implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        Priority priority = new Priority();
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
        Thread thread = new Thread(priority,"1");
        Thread thread2 = new Thread(priority,"2");
        Thread thread3 = new Thread(priority,"3");
        Thread thread4 = new Thread(priority,"4");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(3);
        thread3.setPriority(8);
        thread4.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
