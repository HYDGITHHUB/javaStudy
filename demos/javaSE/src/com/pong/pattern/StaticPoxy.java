package com.pong.pattern;

public class StaticPoxy {
    public static void main(String[] args) {
//        Wedding wedding = new Wedding(new You());
//        wedding.marry();
        new Wedding(new You()).marry();
    }
}

interface Marry{
    public void marry();
}

class You implements Marry {
    @Override
    public void marry() {
        System.out.println("你要结婚了");
    }
}

class Wedding implements Marry {
   private You you;


    public Wedding(You you) {
        this.you = you;
    }

    @Override
    public void marry() {
        before();
        you.marry();
        after();
    }

    public void before() {
        System.out.println("布置场景");
    }

    public void after() {
        System.out.println("收尾款");
    }
}