package com.pong.changeByOneself;

public class HungrySignal {
    private HungrySignal() {

    }
    private final static HungrySignal hungrySignal = new HungrySignal();

    public static HungrySignal getInstance() {
        return hungrySignal;
    }
}
