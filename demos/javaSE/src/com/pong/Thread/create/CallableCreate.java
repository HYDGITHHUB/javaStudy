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
