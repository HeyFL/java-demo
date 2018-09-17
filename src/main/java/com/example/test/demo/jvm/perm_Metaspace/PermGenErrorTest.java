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
     * JDK8不会溢出 TODO 可能是因为把常量池移到元空间里了？
     */
    private static void finalPoolFullError() {
        long i = 0;
        List<String> stringList = new LinkedList<>();
        while (true) {
            stringList.add(String.valueOf(i++).intern());
        }
    }
}
