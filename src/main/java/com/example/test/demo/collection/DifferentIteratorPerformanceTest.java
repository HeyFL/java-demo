package com.example.test.demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 比较不同itertor的遍历性能
 * 编译后  3个的源码几乎一致(都是以 while 实现)
 */
public class DifferentIteratorPerformanceTest {
    final int LIST_SIZE = 1000000;
    final int NUM_MAX = 1000000;
    final Random random = new Random(NUM_MAX);
    List<Integer> list = new ArrayList(LIST_SIZE);

    public DifferentIteratorPerformanceTest() {
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(random.nextInt());
        }
    }
    public void testNewFor(){//没啥区别
        for(Integer s : list){
            System.out.println(s);
        }
    }
    public void testFor(){//5553ms 5632ms 5788ms
        for(Iterator i = list.iterator();i.hasNext();){
            System.out.println((Integer) i.next());
        }
    }
    public void testWhile(){
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println((Integer) iterator.next());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DifferentIteratorPerformanceTest test = new DifferentIteratorPerformanceTest();
        long startTime = System.currentTimeMillis();
        test.testWhile();
        System.out.println(System.currentTimeMillis()-startTime+"ms");
    }
}
