/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.freeLock;

import com.example.test.demo.aminoFrame.amino.ds.lockfree.LockFreeList;
import com.example.test.demo.aminoFrame.amino.ds.lockfree.LockFreeVector;
import lombok.Setter;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

@Setter
class AccessListTread implements Runnable {
    private final static int count = 10000;
    Random rand = new Random();
    List list;
    private String operatorType;

    public AccessListTread() {
    }

    @Override
    public void run() {
        //try {
        if ("addAndRemove".equals(operatorType)) {
            for (int i = 0; i < count; i++) {
                //测试随机读写
                addAndRemoveObj(rand.nextInt(count));
            }
        }
        if ("randomRead".equals(operatorType)) {
            for (int i = 0; i < count; i++) {
                //测试随机访问
                getList(rand.nextInt(count));
            }
        }
        //Thread.sleep(rand.nextInt(100));
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }

    private Object getList(int nextInt) {
        return list.get(nextInt);
    }

    private Object addAndRemoveObj(int index) {
        list.add(index);
        list.remove(index);
        return null;
    }

    //test
    public void initCopyOnWriteArrayList() {
        long startTime = System.currentTimeMillis();
        list = new CopyOnWriteArrayList();
        for (int i = 0; i < count; i++)
            list.add(i);
        System.out.println("initCopyOnWriteArrayList 初始化用时间:" + (System.currentTimeMillis() - startTime));
    }

    public void initVector() {
        long startTime = System.currentTimeMillis();
        list = new Vector();
        for (int i = 0; i < count; i++)
            list.add(i);

        System.out.println("initVector 初始化用时间:" + (System.currentTimeMillis() - startTime));
    }

    public void initLockFreeList() {
        long startTime = System.currentTimeMillis();
        list = new LockFreeList();
        for (int i = 0; i < count; i++)
            list.add(i);
        System.out.println("initLockFreeList 初始化用时间:" + (System.currentTimeMillis() - startTime));
    }

    public void initLockFreeVector() {
        long startTime = System.currentTimeMillis();
        list = new LockFreeVector();
        for (int i = 0; i < count; i++)
            list.add(i);
        System.out.println("initLockFreeVector 初始化用时间:" + (System.currentTimeMillis() - startTime));
    }
}