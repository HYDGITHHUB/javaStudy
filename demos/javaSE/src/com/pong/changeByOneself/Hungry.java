package com.pong.changeByOneself;

/**
 * 饿汉式
 */
public class Hungry {
    private Hungry() {
    }

    private final static Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        return hungry;
    }
}
