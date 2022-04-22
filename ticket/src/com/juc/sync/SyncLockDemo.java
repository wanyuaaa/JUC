package com.juc.sync;

/**
 * @author wanyu
 * @createTime 2022-04-22 6:34
 */
public class SyncLockDemo {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName()+"1");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName()+"2");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName()+"3");
                    }
                }
            }
        }, "a").start();
    }
}
