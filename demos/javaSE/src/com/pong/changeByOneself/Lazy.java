package com.pong.changeByOneself;

public class Lazy {
    private static boolean flag = false;

    private Lazy() {
        synchronized (Lazy.class) {
            if (flag == false) {
                flag = true;
            } else {
                throw new RuntimeException("error:尝试使用非法手段破坏单例模式(反射?)");
            }
        }
    }

    private volatile static Lazy lazy;

    public static Lazy getInstance() {
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }
}
