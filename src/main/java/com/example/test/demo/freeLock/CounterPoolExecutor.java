/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.freeLock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class CounterPoolExecutor extends ThreadPoolExecutor {
    public AtomicInteger count = new AtomicInteger(0);//统计次数
    public long startTime = 0;
    public String funcname = "";
    public int TASK_COUNT = 0;

    public CounterPoolExecutor(int corePoolSize, int maximumPoolSize,
                               long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        int l = count.addAndGet(1);
        if (l == TASK_COUNT) {
            System.out.println(funcname + "spend time:" + (System.currentTimeMillis() - startTime));
        }
    }
}