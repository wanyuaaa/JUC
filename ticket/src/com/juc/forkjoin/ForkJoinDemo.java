package com.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wanyu
 * @createTime 2022-04-22 12:59
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        MyTask task = new MyTask(0, 1000);

        ForkJoinPool pool = new ForkJoinPool();

        try {
            ForkJoinTask<Integer> submit = pool.submit(task);
            Integer integer = submit.get();
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

    }
}

class MyTask extends RecursiveTask<Integer> {

    //拆分差值
    private static final Integer VALUE = 10;
    private final int begin;
    private final int end;
    private int result;

    //有參構造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int mid = (begin + end) / 2;
            MyTask task01 = new MyTask(begin, mid);
            MyTask task02 = new MyTask(mid + 1, end);

            task01.fork();
            task02.fork();

            result = task01.join() + task02.join();
        }
        return result;
    }
}