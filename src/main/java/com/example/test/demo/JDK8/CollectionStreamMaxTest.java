/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo.JDK8;

import java.util.*;

/**
 * JDK8  Collection Stream
 * 经测试 JDK8的集合流操作的性能不一定有以前的写法好:
 *      在取 [最大最小值] 的时候,Collections API性能好一点;
 *      在 [排序] 的时候,Stream(尤其是parallelStream[内部使用Fork join实现]),Stream大幅度占优
 * Streams API 是 Java 8 中新发布的 API，主要用于操作 collection 和 streaming 数据。
 * Collections API 会改变数据集状态，而 Streams API 则不会。例如，当你调用Collections.sort(list)时，该方法会对传入的参数进行排序，而调用list.stream().sorted() 则会复制一份数据进行操作，保持原数据不变。
 */
public class CollectionStreamMaxTest {
    private static int tryNum = 5000;
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList();
        for (int i = tryNum; i > 0; i--) {
            list.add(random.nextInt(100000000));
        }
        long start;
        //testForEach(list);
        System.out.println("==========================");
        start = System.currentTimeMillis();
        testMaxAndMin(list);//流取最大值
        System.out.println("流获取最大值 耗时: "+(System.currentTimeMillis()-start)+"ms");
        System.out.println("==========================");
        //Thread.sleep(5000);
        start = System.currentTimeMillis();
        //testMap(list);
        //testReduce(list);
        //testMaxAndMin(list);
        testParallelMaxAndMin(list);//流取最大值
        System.out.println("并发流取最大值 耗时: "+(System.currentTimeMillis()-start)+"ms");
        System.out.println("==========================");
        start = System.currentTimeMillis();
        //testMap(list);
        //testReduce(list);
        //testMaxAndMin(list);
        System.out.println(Collections.max(list));//普通集合取最大值
        System.out.println("Collections原生集合类取最大值 耗时: "+(System.currentTimeMillis()-start)+"ms");
        System.out.println("==========================");


        start = System.currentTimeMillis();
        list.stream().sorted();
        System.out.println("流排序 耗时: "+(System.currentTimeMillis()-start)+"ms");
        System.out.println("==========================");
        start = System.currentTimeMillis();
        list.parallelStream().sorted();
        System.out.println("并发流排序 耗时: "+(System.currentTimeMillis()-start)+"ms");
        System.out.println("==========================");
        start = System.currentTimeMillis();
        Collections.sort(list);
        System.out.println("集合排序 耗时: "+(System.currentTimeMillis()-start)+"ms");
        System.out.println("==========================");
    }

    private static void testMaxAndMin(List<Integer> list) {
        int max = list.stream().max(Comparator.comparing(Integer::intValue)).get();
        System.out.println(max);
    }
    private static void testParallelMaxAndMin(List<Integer> list) {
        int max = list.parallelStream().max(Comparator.comparing(Integer::intValue)).get();
        System.out.println(max);
    }

    private static void testForEach(List<Integer> list) {
        list.stream().sorted().forEach(System.out::println);
    }

    private static void testMap(List<Integer> list) {
        list.stream().map((n) -> n + 1).forEach(System.out::println);
        list.stream().forEach(System.out::println);
    }

    private static void testReduce(List<Integer> list) {
        //int sumCount = list.stream().reduce((a, b) -> a + b).get();等同下面
        int sumCount = list.stream().reduce(0, (a, b) -> a + b);

        System.out.println(list.size());
        System.out.println(sumCount);
    }
}
