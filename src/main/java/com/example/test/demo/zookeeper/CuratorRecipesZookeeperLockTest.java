/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CuratorRecipesZookeeperLockTest {
    static int i = 0;
    //private static String address = "172.16.30.72:2181";//master:2181,worker1:2181,worker2:2181
    private static String address = "127.0.0.1:2182,127.0.0.1:2181,127.0.0.1:2183";//master:2181,worker1:2181,worker2:2181
    private static CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));//静态  不需要close
    //创建锁，为不可重入锁，即是获锁后，不可以再次获取，这里不作例子，使用和重入锁类似
    //InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, lockPath);
    static private ExecutorService pool = Executors.newFixedThreadPool(20);

    static {
        client.start();
    }

    private String PATH = "/zkLockRoot/lock_1";//根
    private InterProcessMutex lock;

    public CuratorRecipesZookeeperLockTest() {
        lock = new InterProcessMutex(client, PATH);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            pool.submit(new Thread(() -> {
                try {
                    new CuratorRecipesZookeeperLockTest().test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    public void test() throws InterruptedException {
        try {
            if (tryLock(30, TimeUnit.SECONDS)) {
                System.out.println(i++);
            }
            //lock.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock();
            //lock.release();
            //client.close();
        }
    }

    private boolean unlock() {
        try {
            lock.release();
            System.out.println(Thread.currentThread() + "  release read lock");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        try {
            System.out.println(Thread.currentThread() + "  acquire read lock");
            return lock.acquire(timeout, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
