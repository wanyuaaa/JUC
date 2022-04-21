package com.juc.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wanyu
 * @createTime 2022-04-22 5:41
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<Object> list = Collections.synchronizedList(new ArrayList<>());

        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        //CopyOnWriteArraySet<Object> objects = new CopyOnWriteArraySet<>();
        //ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
