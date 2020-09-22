package com.pong.Thread.trouble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LockWitch {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Switchs witch = new Switchs();
        Switchs witch2 = new Switchs();
        new Thread(()->{
            witch.one();
        }).start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            witch.two();
        }).start();
    }
}

class Switchs {
    public static synchronized void one() {
        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public  synchronized void two() {
        System.out.println("two");
    }
}
