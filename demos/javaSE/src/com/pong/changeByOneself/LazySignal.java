package com.pong.changeByOneself;

public class LazySignal {
    private boolean flag = false;

    private LazySignal() {
        synchronized (LazySignal.class) {
            if (flag = false) {
                flag = true;
            } else {
                throw new RuntimeException("非法操作...");
            }
        }
    }

    private volatile static LazySignal lazySignal;

    public static LazySignal getInstance() {
        if (lazySignal == null) {
            synchronized (LazySignal.class) {
                if (lazySignal == null) {
                    lazySignal = new LazySignal();
                }
            }
        }
        return lazySignal;
    }
}
