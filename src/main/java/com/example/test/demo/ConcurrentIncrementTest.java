/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentIncrementTest {
    /*
    * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
    * Use, Copy is subject to authorized license.
    */
    ExecutorService pool = Executors.newFixedThreadPool(50);
    final int threadNum = 40;
    final int incrementNum = 9999999;
    public static void main(String[] args) {
        ConcurrentIncrementTest test = new ConcurrentIncrementTest();
        test.mainTest();

    }

    public String mainTest() {

        pool.execute(() -> {
            test1();

        });
        pool.execute(() -> {
            test2();

        });
        pool.execute(() -> {
            test3();

        });
        return "sucess";
    }

    private void test1() {
        LongAdder long1 = new LongAdder();

        long a = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            pool.execute(() -> {
                for (int j = 0; j < incrementNum; j++) {
                    long1.increment();
                }

            });
        }
        pool.execute(() -> {
            while (true) {
                if (long1.floatValue() == threadNum * incrementNum) {
                    System.out.println((System.currentTimeMillis() - a) + "ms");
                    System.out.println("LongAdder = " + long1);
                    return;
                }
            }
        });
    }

    private void test2() {
        AtomicLong long2 = new AtomicLong();
        long a = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            pool.execute(() -> {
                for (int j = 0; j < incrementNum; j++) {
                    long2.incrementAndGet();
                }
            });
        }
        //lambda表达式 ()-> 替换new Runnable(){run()
        pool.execute(() -> {
            while (true) {
                if (long2.floatValue() == threadNum * incrementNum) {
                    System.out.println((System.currentTimeMillis() - a) + "ms");
                    System.out.println("AtomicLong = " + long2);
                    return;
                }
            }
        });
    }

    private void test3() {
        AtomicInteger integer1 = new AtomicInteger();
        long a = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            pool.execute(() -> {
                for (int j = 0; j < incrementNum; j++) {
                    integer1.getAndIncrement();
                }

            });
        }
        pool.execute(() -> {
            while (true) {
                if (integer1.floatValue() == threadNum * incrementNum) {
                    System.out.println((System.currentTimeMillis() - a) + "ms");
                    System.out.println("AtomicInteger = " + integer1);
                    return;
                }
            }

        });
    }

}
