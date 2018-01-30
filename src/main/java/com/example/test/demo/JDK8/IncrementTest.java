/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.JDK8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 测试比 AtomicLong 更加搞笑的无锁 LongAdder  (顺便对比 AtomicInteger)
 * 实测,JDK8的LongAdder更加高效(特别在高并发情况下)
 */
public class IncrementTest {

    final int MAX_THREAD_NUM = 60;
    final int INCEMENT_NUM = 10000000;
    CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD_NUM);

    public static void main(String[] args) throws InterruptedException {
        IncrementTest test = new IncrementTest();
        System.out.println("进程正式开始");

        //test.testAtomicLong();//10724ms 10697ms 11044ms 11610ms

        //test.testLongAdder();//3005ms 3131ms 3426ms 3086ms

        test.testAtomicInteger();//11369ms 11049ms 11341ms 11273ms

    }

    private void testAtomicInteger() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_NUM);
        AtomicInteger atomicInteger = new AtomicInteger();
        long a = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            pool.submit(new Thread(() -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < INCEMENT_NUM; j++) {
                    atomicInteger.incrementAndGet();
                }
            }));
        }
        countDownLatch.await();

        while (true) {
            if (atomicInteger.floatValue() == MAX_THREAD_NUM * INCEMENT_NUM) {
                System.out.println(atomicInteger.floatValue());
                System.out.println((System.currentTimeMillis() - a) + "ms");
                pool.shutdown();
                return;
            }
        }
    }

    private void testAtomicLong() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_NUM);
        AtomicLong atomicLong = new AtomicLong();
        long a = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            pool.submit(new Thread(() -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < INCEMENT_NUM; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        countDownLatch.await();

        while (true) {
            if (atomicLong.floatValue() == MAX_THREAD_NUM * INCEMENT_NUM) {
                System.out.println(atomicLong.floatValue());
                System.out.println((System.currentTimeMillis() - a) + "ms");
                pool.shutdown();
                return;
            }
        }
    }

    private void testLongAdder() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_NUM);
        LongAdder longAdder = new LongAdder();
        long a = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            pool.submit(new Thread(() -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < INCEMENT_NUM; j++) {
                    longAdder.increment();
                }
            }));
        }
        countDownLatch.await();

        while (true) {
            if (longAdder.floatValue() == MAX_THREAD_NUM * INCEMENT_NUM) {
                System.out.println(longAdder.floatValue());
                System.out.println((System.currentTimeMillis() - a) + "ms");
                pool.shutdown();
                return;
            }
        }
    }




}
