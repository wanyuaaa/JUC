package com.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanyu
 * @createTime 2022-04-22 5:14
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.del();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "b").start();
    }
}

class Share {
    private int num = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "::" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void del() throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "::" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}