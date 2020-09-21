package com.pong.Thread.trouble;

//死锁:多个线程互相拥有对象拥有的资源并且想要获得对象的资源.
public class DeadLock {
    public static void main(String[] args) {
        Food food = new Food();
        Water water = new Water();
        People people = new People(1, food, water);
        People people2 = new People(2, food, water);
        new Thread(people).start();
        new Thread(people2).start();
    }
}

class Food {

}

class Water {

}

class People implements Runnable {
    int choice;
    Food food;
    Water water;

    public People(int choice, Food food, Water water) {
        this.choice = choice;
        this.food = food;
        this.water = water;
    }


    @Override
    public void run() {
        if (choice == 1) {
            synchronized (food) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获得食物");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            synchronized (water) {
                System.out.println(Thread.currentThread().getName() + "获得水");
            }
        } else {
            synchronized (water) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获得水");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (food) {
                System.out.println(Thread.currentThread().getName() + "获得食物");
            }
        }
    }
}