
package com.example.test.demo.jvm.direct_memory;

import sun.misc.Unsafe;
import sun.misc.VM;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * JDK7 JDK8测试结果一致:
 * <p>
 * -XX:MaxDirectMemorySize=128m 这个设置为多少就使用多少直接内存
 * -Xmx800m 如果上面的配置不存在 会使用堆最大值来作为直接内存最大值(实际上会比800少一些)
 * <p>
 * 直接内存会存储在物理内存与虚拟内存里
 *
 * @author caizq
 * @date 2018/9/16
 * @since v1.0.0
 */
public class DirectMemoryOOMTest {
    private static Integer _1MB = 1 * 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException, InterruptedException, ClassNotFoundException, NoSuchFieldException {
        System.out.println("当前虚拟机直接内存最大值为" + VM.maxDirectMemory() / 1024 / 1024 + "MB");

        //unsafeAllocate(); 这个貌似无视设置参数

        testAllocateDirector();
    }

    /**
     * 通过ByteBuffer申请直接内存
     *
     * @throws Exception
     */
    public static void testAllocateDirector() {
        long totalMB = 0;
        List list = new LinkedList();
        try {
            while (true) {
                list.add(ByteBuffer.allocateDirect(_1MB));
                totalMB += 1;
                //Thread.sleep(100);
            }
        } catch (Throwable e) {
            System.out.println("申请总直接内存:" + totalMB + "MB");
            e.printStackTrace();
        }
    }

    /**
     * 与下面两个参数没关系 个人认为是Unsafe不会检查这两个参数,而是直接去申请
     * -XX:MaxDirectMemorySize=128m -Xmx500m
     * 测试会溢出  但是看不出内存会增加
     *
     * @throws IllegalAccessException
     */
    private static void unsafeAllocate() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);

        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        long totalMB = 0;
        try {
            while (true) {
                totalMB += 1;
                unsafe.allocateMemory(_1MB);
                //TimeUnit.NANOSECONDS.sleep(1);
                //Thread.sleep(1);
            }
        } catch (Throwable e) {
            System.out.println("申请总直接内存:" + totalMB + "MB");
        }
    }
}
