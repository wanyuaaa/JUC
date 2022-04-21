package com.juc.sync;

/**
 * @author wanyu
 * @createTime 2022-04-22 4:56
 *
 * 2.线程通信
 */
public class ThreadDemo1 {
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

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "c").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.del();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "d").start();

    }
}

class Share {
    private int num = 0;

    public synchronized void add() throws InterruptedException {
        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "::" + num);

        this.notifyAll();
    }

    public synchronized void del() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "::" + num);

        this.notifyAll();
    }
}