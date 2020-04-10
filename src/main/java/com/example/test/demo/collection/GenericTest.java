package com.example.test.demo.collection;

import java.util.*;

/**
 * 泛型测试
 */
public class GenericTest {
    public static void main(String[] args) throws InterruptedException {
        List a = new ArrayList();
        a.add("a");
        a.add("b");
        List<?> a1 = a;
        System.out.println(a1.get(1));

        HashMap<String,String> map = new HashMap();
        map.put("a","1");
        map.put("a","1");
        map.entrySet().forEach(System.out::println);

        HashSet<String> set = new HashSet();
        set.add("1");
        set.add("1");
        set.forEach(System.out::println);
    }
}
