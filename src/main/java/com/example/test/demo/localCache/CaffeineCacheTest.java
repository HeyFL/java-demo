/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.localCache;

import com.example.test.demo.domain.Student;
import com.example.test.demo.util.MethodSpendUtil;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 使用同步方式与Ehcache以同样逻辑处理对比（INITIAL_CAPACITY = 9999999  maximumSize=100000000）
 * 单线程
 * Ehcache:
 *      putEle耗时23025ms
 *      getEle耗时4235ms
 * Caffeine:
 *      putSyn耗时13491ms
 *      getSyn耗时711ms
 *
 * @author caizq
 * @date 2018/8/28
 * @since v1.0.0
 */
@Slf4j
public class CaffeineCacheTest {

    //private static final String KEY = "stu";
    public static final int INITIAL_CAPACITY = 9999999;
    private static List<Student> studentList=new ArrayList<>(INITIAL_CAPACITY);
    private static List<String> keys=new ArrayList<>(INITIAL_CAPACITY);

    private static Map map = new ConcurrentHashMap(INITIAL_CAPACITY);

    static {
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            keys.add(Integer.toString(i));
            studentList.add(new Student( Integer.toString(new Random().nextInt(INITIAL_CAPACITY)),new Random().nextInt(INITIAL_CAPACITY),Integer.toString(new Random().nextInt(INITIAL_CAPACITY))));
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Cache cacheUtil = synLoadingTest();
        MethodSpendUtil.spendMs(new CaffeineCacheTest(), "putSyn",cacheUtil);
        MethodSpendUtil.spendMs(new CaffeineCacheTest(), "getSyn",cacheUtil);

        MethodSpendUtil.spendMs(new CaffeineCacheTest(), "putConCurrentHashMap");
        MethodSpendUtil.spendMs(new CaffeineCacheTest(), "getConCurrentHashMap");
    }

    private static void getSyn(Cache cacheUtil) {
        for (int i = 0; i < keys.size(); i++) {
            int finalI = i;
            Student student = (Student) cacheUtil.get(keys.get(i), key -> createExpensiveGraph((String) key));
            //System.out.println(student);
        }
    }

    private static void putSyn(Cache cacheUtil) {
        for (int i = 0; i < studentList.size(); i++) {
            cacheUtil.put(keys.get(i),studentList.get(i) );
        }
    }
    private static void getConCurrentHashMap() {
        for (int i = 0; i < keys.size(); i++) {
            Student student = (Student)map.get(keys.get(i));
            //System.out.println(student);
        }
    }
    private static void putConCurrentHashMap() {
        for (int i = 0; i < studentList.size(); i++) {
            map.put(keys.get(i),studentList.get(i) );
        }
    }

    /**
     * 主动加载
     */
    public void manualTest() {
        Cache<String, Object> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100000000)
                .build();

        String key = "stu";
        // 根据key查询一个缓存，如果没有返回NULL
        Student graph = (Student) manualCache.getIfPresent(key);
        // 根据Key查询一个缓存，如果没有调用createExpensiveGraph方法，并将返回值保存到缓存。
        // 如果该方法返回Null则manualCache.get返回null，如果该方法抛出异常则manualCache.get抛出异常
        //graph = (Student) manualCache.get(key, k -> createExpensiveGraph(k));
        // 将一个值放入缓存，如果以前有值就覆盖以前的值
        manualCache.put(key, graph);
        // 删除一个缓存
        manualCache.invalidate(key);

        ConcurrentMap<String, Object> map = manualCache.asMap();
        manualCache.invalidate(key);
    }

    private static Student createExpensiveGraph(String key) {
        System.out.println("缓存不存在或过期，调用了createExpensiveGraph方法获取缓存key的值");

        return new Student("Tom", 12, "男");
    }


    /**
     * 同步加载
     */
    public static Cache synLoadingTest() {
        LoadingCache<String, Object> loadingCache = Caffeine.newBuilder()
                .maximumSize(100000000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(key -> createExpensiveGraph(key));

        return loadingCache;
    }

    /**
     * 异步加载
     */
    public static AsyncLoadingCache asynLoadingTest() {
        AsyncLoadingCache<String, Object> asynLoading = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(key -> createExpensiveGraph(key));
        return asynLoading;
    }



}
