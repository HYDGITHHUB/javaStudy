package com.pong.Thread.demos;

import java.util.ArrayList;
import java.util.List;

public class IntoList {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (list) {
                list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
