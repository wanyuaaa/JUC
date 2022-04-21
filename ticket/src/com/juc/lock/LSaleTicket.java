package com.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanyu
 * @createTime 2022-04-22 4:40
 */
public class LSaleTicket {
    public static void main(String[] args) {
        LTicket lTicket = new LTicket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "c").start();

    }
}

class LTicket {
    private int num = 30;

    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖出:" + (num--) + "剩下:" + num);
            }
        } finally {
            lock.unlock();
        }

    }
}