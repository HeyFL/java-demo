package com.example.test.demo.jvm.stack;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * -Xss128k
 */
@Data
public class StackErrorTest {
    private static AtomicLong i = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        //1. 测试错误StackOverFlow
        //testStackOverFlow();


        //2.测试错误OOM
        testOOM();

    }

    private static void testStackOverFlow() {
        new Thread(() -> {
            StackErrorTest testData = new StackErrorTest();
            try {

                testData.test1();
            } catch (Throwable e) {
                System.out.println(Thread.currentThread().getName() + " " + i.get());

            }
        }).start();
    }

    /**
     * 2.测试错误OOM
     * (结果：与每个线程栈空间-Xss无关 只与jvm可分配的『所有栈』大小总和有关)
     * 『所有栈』大小jvm 除去其他空间，剩下的可分配内存有关
     */
    private static void testOOM() {
        try {
            while (true) {
                new Thread(() -> {
                    StackErrorTest testData = new StackErrorTest();
                    i.addAndGet(1);
                    testData.test2();
                }).start();
            }
        } catch (Throwable e) {
            System.out.println(i.get());
            e.printStackTrace();
        }
    }

    public void test1() {
        i.addAndGet(1);
        test1();
    }

    public void test2() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
