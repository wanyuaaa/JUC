package com.juc.sync;

/**
 * @author wanyu
 * @createTime 2022-04-22 4:26
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "c").start();
    }
}

class Ticket {
    private int num = 30;

    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + ":卖出:" + (num--) + "剩下:" + num);
        }
    }
}