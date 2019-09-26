package com.example.test.demo.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapTest {
    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap<Integer, String> chm = new ConcurrentHashMap<>();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        newFixedThreadPool.submit(new Runnable() {
            public void run() {
                for(int i = 0;i <1000000;i++){
                    chm.put(123, "asd"+String.valueOf(i));
                }
                System.out.println("put线程结束");
            }
        });newFixedThreadPool.submit(new Runnable() {
            public void run() {
                for(int i = 9999;i <2000000;i++){
                    chm.put(123, "asd"+String.valueOf(i));
                }
                System.out.println("put线程结束");
            }
        });
        newFixedThreadPool.shutdown();

        while (!newFixedThreadPool.isTerminated()) {
            Thread.sleep(1000);
        }
        System.out.println(chm.get(123));
    }

}
