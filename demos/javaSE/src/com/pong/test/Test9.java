package com.pong.test;

public class Test9 {
    private Test9() {

    }
    private volatile static Test9 test;

    public static Test9 getInstance() {
        if (test == null) {
            synchronized (test) {
                if (test == null) {
                    test = new Test9();
                }
            }
        }
        return test;
    }
}








//public class Test9 {
//    private Test9() {
//
//    }
//
//    private volatile static Test9 test9;
//
//    public static Test9 getInstance() {
//        if (test9 == null) {
//            synchronized (test9) {
//                if (test9 == null) {
//                    test9 = new Test9();
//                }
//            }
//        }
//        return test9;
//    }
//}
