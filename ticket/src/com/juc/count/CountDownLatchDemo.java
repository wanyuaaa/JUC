package com.juc.count;

import java.util.concurrent.CountDownLatch;

/**
 * @author wanyu
 * @createTime 2022-04-22 8:14
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开");

                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("锁门");
    }
}
