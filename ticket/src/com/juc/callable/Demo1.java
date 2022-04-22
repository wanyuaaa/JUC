package com.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wanyu
 * @createTime 2022-04-22 7:06
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new Thread1(), "a").start();

        FutureTask<Integer> futureTask = new FutureTask<>(new Thread2());
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "::callable");
            return 1024;
        });

        new Thread(futureTask2,"a").start();

        System.out.println(futureTask2.get());
    }
}

class Thread1 implements Runnable {
    @Override
    public void run() {

    }
}

class Thread2 implements Callable {
    @Override
    public Object call() throws Exception {
        return 200;
    }
}