package com.pong.test;

public class Test5 extends TestAbstract {

    public static void main(String[] args) {
        long l = Runtime.getRuntime().maxMemory();
        long max = Runtime.getRuntime().totalMemory();
        System.out.println(l);
        System.out.println(max);
    }
    @Override
    public void aVoid() {
        super.aVoid();
    }
}
