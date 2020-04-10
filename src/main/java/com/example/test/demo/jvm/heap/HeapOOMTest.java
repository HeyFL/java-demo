package com.example.test.demo.jvm.heap;

import java.util.LinkedList;
import java.util.List;

/**
 * <--输出JVM参数-->
 * -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal  -XX:+PrintCommandLineFlags -showversion
 *
 * <--输出OOM错误-->
 * -XX:+HeapDumpOnOutOfMemoryError
 *
 * <--GC日志参数-->
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps and -XX:+PrintGCDateStamps
 *
 * -Xms20m -Xmx20m
 */
public class HeapOOMTest {
    public static void main(String[] args) {
        List<Student> list = new LinkedList<>();
        while (true) {
            list.add(new Student());
        }
    }

    static class Student {

    }
}
