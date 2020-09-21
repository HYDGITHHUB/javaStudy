# JUC 多线程

## 1. 创建线程的方式

### 1.1 继承 Thread 类

>Thread 类本质也是实现了 Runnable 接口,通过继承 Thread,然后实现 run 方法,然后调用类的 start 方法启动线程类.

```java
package com.pong.Thread.create;

public class ThreadCreate extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("run线程---" + i);
        }
    }

    public static void main(String[] args) {

        ThreadCreate threadCreate = new ThreadCreate();
        threadCreate.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("main线程---"+ i);
        }
    }
}

```



### 1.2 实现 Runnable 接口

>通过实现 Runnable 接口中的 run 方法实现,调用的时候与继承的区别是:要通过手动创建线程来代理启动
>
>好处:灵活方便



```java
package com.pong.Thread.create;

public class RunnableCreate implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            System.out.println("implements Runnable 实现--" + i);
        }
    }

    public static void main(String[] args) {
        RunnableCreate runnableCreate = new RunnableCreate();

        new Thread(runnableCreate).start();

        new Thread(()->{
            for (int i = 0; i < 200; i++) {
                System.out.println("通过拉姆达实现多线程---" + i);
            }
        },"myThread").start();

        for (int i = 0; i < 200; i++) {
            System.out.println("主线程---" + i);
        }
    }
}

```

### 1.3 实现 Callable 接口

>通过实现 Callable 接口来实现多线程,与 Runnable 接口区别的是,实现 Callable 接口的时候具有返回值,可以返回指定的类型(初始阶段目前了解即可)

```java
package com.pong.Thread.create;

import java.util.concurrent.*;

public class CallableCreate implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println("Callable实现--" + i);
            Thread.sleep(200);
        }
        return true;
    }

    public static void main(String[] args) {
        CallableCreate callableCreate = new CallableCreate();

        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> res1 = service.submit(callableCreate);
        for (int i = 0; i < 200; i++) {
            System.out.println("main 主线程---" + i);
        }
        //获取结果
        try {
            Boolean result = res1.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //关闭执行
        service.shutdown();
    }
}

```



### 1.4 存在问题

>多线程情况下,存在数据紊乱,所以需要加锁

### 1.5 龟兔赛跑案例

```java
package com.pong.Thread.demos;

public class RabbitAndTortoise implements Runnable {

    private Boolean flag = false;

    @Override
    public void run() {
        for (int i = 0; i <= 200; i++) {
            isFinish(i);
            if (flag) break;
            if (Thread.currentThread().getName().equals("兔子") && i %20 == 0) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    public Boolean isFinish(int steps) {
        if (steps >= 200){
            System.out.println(Thread.currentThread().getName()+"获胜");
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        RabbitAndTortoise rabbitAndTortoise = new RabbitAndTortoise();
        new Thread(rabbitAndTortoise,"乌龟").start();
        new Thread(rabbitAndTortoise,"兔子").start();
    }
}
```

