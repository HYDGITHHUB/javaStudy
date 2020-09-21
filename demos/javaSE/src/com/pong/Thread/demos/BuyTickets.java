package com.pong.Thread.demos;

public class BuyTickets {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        new Thread(tickets,"小明").start();
        new Thread(tickets,"小红").start();
        new Thread(tickets,"小李").start();
    }
}

class Tickets implements Runnable {
    private Integer nums = 100;
    private Boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            if (nums <= 0) {
                flag = false;
                break;
            }
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + "-->" + nums--);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
