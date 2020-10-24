package com.pong.tengxun;

import java.util.concurrent.TimeUnit;

public abstract class Test {
    public abstract void a();
    static {

    }
//    public static void main(String[] args) {
//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//    public void A() throws InterruptedException {
//        Test test = new Test();
//        synchronized (test) {
//            this.wait();
//        }
//    }
}

abstract class b extends Test {
    StringBuffer a;
    StringBuilder b;
}

class c extends b {
    @Override
    public void a() {

    }
}