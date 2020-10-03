package com.pong.test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

//-Xms1m -Xmx1m -XX:+PrintGCDetails
public class Test6 implements Comparable<Test6> {
    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();
        System.out.println(threadLocal.get());
//        byte[] bytes = new byte[1024*1024*1];
//        ArrayList<Test6> arrayList = new ArrayList<>();
//        while (true) {
//            arrayList.add(new Test6());
//        }
    }

    @Override
    public int compareTo(Test6 o) {
        return 0;
    }
}
