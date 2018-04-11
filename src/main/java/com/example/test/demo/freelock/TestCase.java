/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.freelock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * 相关无锁类来自: https://sourceforge.net/projects/amino-cbbs/files/
 * initVector 初始化用时间:2
 * testVector  randomReadspend time:369
 * initVector 初始化用时间:1
 * testVector  addAndRemovespend time:9827
 * initLockFreeList 初始化用时间:15
 * testLockFreeList  randomReadspend time:77
 * initLockFreeList 初始化用时间:8
 * testLockFreeList  addAndRemovespend time:59
 * initLockFreeVector 初始化用时间:18
 * testLockFreeVector  randomReadspend time:330
 * initLockFreeVector 初始化用时间:3
 * testLockFreeVector addAndRemovespend time:65
 * initCopyOnWriteArrayList 初始化用时间:55
 * testCopyOnWriteArrayList  randomReadspend time:336
 * initCopyOnWriteArrayList 初始化用时间:43
 * testCopyOnWriteArrayList  addAndRemovespend time:90470
 */
public class TestCase {
    public static void main(String[] args) throws InterruptedException {

        //测试Vector
        AccessListTread task2 = new AccessListTread();
        task2.setOperatorType("randomRead");
        task2.initVector();
        doTest(task2, "testVector  randomRead");

        AccessListTread task3 = new AccessListTread();
        task3.setOperatorType("addAndRemove");
        task3.initVector();
        doTest(task3, "testVector  addAndRemove");

        //测试LockFreeList
        AccessListTread task7 = new AccessListTread();
        task7.initLockFreeList();
        task7.setOperatorType("randomRead");
        doTest(task7, "testLockFreeList  randomRead");

        AccessListTread task4 = new AccessListTread();
        task4.initLockFreeList();
        task4.setOperatorType("addAndRemove");
        doTest(task4, "testLockFreeList  addAndRemove");

        //测试LockFreeVector
        AccessListTread task5 = new AccessListTread();
        task5.initLockFreeVector();
        task5.setOperatorType("randomRead");
        doTest(task5, "testLockFreeVector  randomRead");

        AccessListTread task6 = new AccessListTread();
        task6.initLockFreeVector();
        task6.setOperatorType("addAndRemove");
        doTest(task6, "testLockFreeVector addAndRemove");

        //测试CopyOnWriteArrayList
        AccessListTread task = new AccessListTread();
        task.initCopyOnWriteArrayList();
        task.setOperatorType("randomRead");
        doTest(task, "testCopyOnWriteArrayList  randomRead");

        AccessListTread task1 = new AccessListTread();
        task1.setOperatorType("addAndRemove");
        task1.initCopyOnWriteArrayList();
        doTest(task1, "testCopyOnWriteArrayList  addAndRemove");
    }

    public static void doTest(AccessListTread task, String name) throws InterruptedException {
        CounterPoolExecutor pool = new CounterPoolExecutor(2000, 2000, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        pool.TASK_COUNT = 500;
        pool.funcname = name;
        pool.startTime = System.currentTimeMillis();
        for (int i = 0; i < pool.TASK_COUNT; i++)
            pool.submit(task);//测试线程：500 每条线程为集合添加20000条数据
        pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(100);
        }
    }

}