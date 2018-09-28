package com.example.test.demo.jvm.perm_Metaspace;

import java.util.LinkedList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * JDK8下以上配置无效
 */
public class PermGenErrorTest {
    public static void main(String[] args) throws InterruptedException {
        finalPoolFullError();


    }

    /**
     * 测试常量池导致 溢出
     * JDK7 jDK8均不会溢出
     * 因为JDK7、8都把常量池移到堆里了
     *
     * 参考 http://java-performance.info/string-intern-in-java-6-7-8/
     * JDK7开始  所有字符串现在都位于堆中
     * All strings are now located in the heap, as most of other ordinary objects, which allows you to manage only the heap size while tuning your application
     */
    private static void finalPoolFullError() {
        long i = 0;
        List<String> stringList = new LinkedList<>();
        while (true) {
            stringList.add(String.valueOf(i++).intern());
        }
    }
}
