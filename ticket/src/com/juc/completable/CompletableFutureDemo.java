package com.juc.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author wanyu
 * @createTime 2022-04-22 13:14
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        //异步调用，没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + "a"));

        //异步调用，有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "b");
            int i = 1 / 0;
            return 1024;
        });

        completableFuture2.whenComplete((t, u) -> {
            System.out.println("----t----" + t);//返回值
            System.out.println("----u----" + u);//异常信息
        });
    }
}
