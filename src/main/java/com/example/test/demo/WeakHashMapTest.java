package com.example.test.demo;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * WeakHashMap的 key 不再被其他人『强』引用时，遇到系统 gc 时，回收该 kv
 */
public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        String a = new String("a");
        String b = new String("b");
        String c = new String("c");
        String d = new String("d");
        WeakHashMap weakHashMap = new WeakHashMap();
        HashMap hashMap = new HashMap();
        weakHashMap.put(a, "aaa");
        weakHashMap.put(b, "bbb");
        hashMap.put(c, "ccc");
        hashMap.put(d, "ddd");

        a = null;
        c = null;
        System.gc();

        System.out.println("=====WeakHashMap======");
        weakHashMap.forEach((k, v) -> {
            //System.out.println(k);
            System.out.println(v);
        });
        System.out.println("=====HashMap======");
        hashMap.forEach((k, v) -> System.out.println(v));

    }
}
