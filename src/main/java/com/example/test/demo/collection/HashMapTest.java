/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo.collection;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试Hash在并发情况下,小几率产生死循环的问题
 * JDK1.8经过优化后...还是会产生死循环
 */
public class HashMapTest {
    private HashMap<Integer, Integer> map = new HashMap<>(2);
    static ExecutorService pool = Executors.newFixedThreadPool(50);
    int testNum = 10;
    private volatile CountDownLatch countor = new CountDownLatch(testNum);
    boolean taskDoneFlag = false;

    public HashMapTest() {
        System.out.println("map1 = " + map);
        for (int j = 0; j < testNum; j++) {
            pool.submit(()->{
                    for (int i = 0; i < 50000; i++) {
                        map.put(new Integer(i), Integer.valueOf(i));
                    }
                    System.out.println("count = "+countor.getCount());
                    countor.countDown();
                }
            );
        }
        pool.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("等待子线程完成");
                    countor.await();
                    System.out.println("子线程已完成,map大小为: " + map.size());
                    Thread.sleep(100);
                    //map.get(9999999);
                    new HashMapTest();
//                    pool.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {

        new HashMapTest();


    }
}