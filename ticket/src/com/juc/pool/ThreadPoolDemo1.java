package com.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanyu
 * @createTime 2022-04-22 10:15
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //1 1:n
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //2 1:1
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //3 1:n+
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 20; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "ing...");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
