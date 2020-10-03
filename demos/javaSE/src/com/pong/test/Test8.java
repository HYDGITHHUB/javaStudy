package com.pong.test;

public class Test8 {
    private Test8() {

    }

    private final static Test8 test = new Test8();

    public static Test8 getInstance() {
        return test;
    }
}
