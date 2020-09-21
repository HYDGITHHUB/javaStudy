package com.pong.Thread.state;

//不建议直接使用 stop 方法进行停止线程,而是使用标识符进行判定
public class Stop implements Runnable {
    private Boolean flag = true;
    private int nums = 100;
    @Override
    public void run() {
        while (flag) {
            if (nums <= 0) {
                flag = false;
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + nums--);
        }
    }

    public static void main(String[] args) {
        Stop stop = new Stop();
        new Thread(stop).start();
        new Thread(stop).start();
        new Thread(stop).start();
        new Thread(stop).start();
        new Thread(stop).start();
    }
}
















//package com.pong.Thread.state;
//
//public class Stop {
//    public static void main(String[] args) {
//        Ticket ticket = new Ticket();
//        new Thread(ticket).start();
//        new Thread(ticket).start();
//        new Thread(ticket).start();
//    }
//}
//
//class Ticket implements Runnable {
//    private Boolean flag;
//    int nums = 100;
//    @Override
//    public void run() {
//        while (true) {
//            if (nums <= 0) break;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "--->" + nums--);
//        }
//    }
//}