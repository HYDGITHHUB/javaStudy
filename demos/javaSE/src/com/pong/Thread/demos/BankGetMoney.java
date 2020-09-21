package com.pong.Thread.demos;

public class BankGetMoney {
    public static void main(String[] args) {
        Account account = new Account(100,"生活费");
        GetMoney you = new GetMoney(account,50,"小明");
        GetMoney she = new GetMoney(account,100,"小红");
        new Thread(you).start();
        new Thread(she).start();
    }
}

class Account {
    int balance;
    String name;

    public Account(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}

class  GetMoney implements Runnable {
    Account account;
    int getNums;
    int balance;
    int own;
    String name;

    public GetMoney(Account account, int getNums, String name) {
        this.account = account;
        this.getNums = getNums;
        this.balance = account.balance;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (account) {
            Thread.currentThread().setName(name);
            if (account.balance - getNums < 0) {
                System.out.println("余额不足");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.balance = account.balance - getNums;
            own = own + getNums;
            System.out.println(account.name + "账户剩余:" + account.balance);
            System.out.println(name + "拥有" + own);
        }

    }
}






//package com.pong.Thread.demos;
//
//public class BankGetMoney {
//    private Boolean flag;
//    public static void main(String[] args) {
//        Account account = new Account("小明",100);
//        Buy buy = new Buy(account,50);
//        Buy buy2 = new Buy(account,99);
//        new Thread(buy,"小明").start();
//        new Thread(buy,"小红").start();
//        new Thread(buy2,"小李").start();
//    }
//}
//
//class Account {
//    private String name;
//    private int balance;
//
//    public Account(String name, int balance) {
//        this.name = name;
//        this.balance = balance;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getBalance() {
//        return balance;
//    }
//}
//
//class Buy implements Runnable {
//
//    private Account account;
//    private String name;
//    private int balance;
//    private int own;
//    private int getNums;
//
//    public Buy(Account account,int getNums) {
//        this.account = account;
//        this.name = this.account.getName();
//        this.balance = this.account.getBalance();
//        this.own = 0;
//        this.getNums = getNums;
//    }
//
//    @Override
//    public void run() {
//        if (balance <= getNums) {
//            System.out.println("余额不足");
//        } else {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            balance = balance - getNums;
//            own = getNums;
//            System.out.println(Thread.currentThread().getName()+"余额" + balance + "拥有" + own);
//        }
//    }
//}