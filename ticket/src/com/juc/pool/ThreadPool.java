package com.juc.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wanyu
 * @createTime 2022-04-22 12:43
 * <p>
 * 参数：
 * <p>
 * corePoolSize–池中要保留的线程数，即使它们处于空闲状态，除非设置了allowCoreThreadTimeOut
 * <p>
 * maximumPoolSize–池中允许的最大线程数
 * <p>
 * keepAliveTime–当线程数大于核心时，这是多余空闲线程在终止前等待新任务的最长时间。
 * <p>
 * unit–keepAliveTime参数的时间单位
 * <p>
 * workQueue–用于在任务执行前保存任务的队列。此队列将仅包含execute方法提交的可运行任务。
 * <p>
 * threadFactory–执行器创建新线程时使用的工厂
 * <p>
 * handler–由于达到线程边界和队列容量而阻止执行时使用的处理程序
 * <p>
 * 抛出：
 * <p>
 * IllegalArgumentException–如果以下情况之一成立：corePoolSize<0 keepAliveTime<0 maximumPoolSize<=0 maximumPoolSize<corePoolSize
 * <p>
 * NullPointerException–如果workQueue或threadFactory或handler为null
 */
public class ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor myPool = new ThreadPoolExecutor(
                2,//
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 20; i++) {
                myPool.execute(() -> System.out.println(Thread.currentThread().getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myPool.shutdown();
        }

    }
}
