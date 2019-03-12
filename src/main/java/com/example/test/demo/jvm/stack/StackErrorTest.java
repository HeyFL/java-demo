package com.example.test.demo.jvm.stack;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * StackOverFlow每次深度不一样是因为JIT优化
 * -Djava.compiler=NONE禁用JIT优化后每次深度一样
 *
 *
 * 测试参数：-Xss128k
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

    /**
     * StackOverFlow每次深度不一样是因为JIT优化
     * -Djava.compiler=NONE禁用JIT优化后每次深度一样
     *
     *
     * 测试参数：-Xss128k
     */
    private static void testStackOverFlow() {
        //new Thread(() -> {
            StackErrorTest testData = new StackErrorTest();
            try {

                testData.test1();
            } catch (Throwable e) {
                System.out.println(Thread.currentThread().getName() + " " + i.get());

            }
        //}).start();
    }


    //private static int ONE_MB=1024*1024;
    //private static byte[] bytes = new byte[1024*ONE_MB];
    /**
     * 2.测试错误OOM
     * 测试1: -Xss1g -Xms20m -Xmx1g
     * 测试2: -Xss1g -Xms20m -Xmx2g
     * (结果：【java虚拟机栈】大小-Xss无关 只与jvm可分配的『所有栈』大小总和有关)
     * 【java虚拟机栈】大小为jvm 除去其他空间，剩下的可分配内存有关
     */
    private static void testOOM() throws InterruptedException {
        try {
            while (true) {
                new Thread(() -> {
                    StackErrorTest testData = new StackErrorTest();
                    i.addAndGet(1);
                    testData.test2();
                }).start();
            }
        } catch (Throwable e) {
            Thread.sleep(1000);//个别线程还没执行到 i.addAndGet(1);
            System.out.println("创建了"+i.get()+"条线程");
            e.printStackTrace();
        }
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

    public void test1() {
        i.addAndGet(1);
        test1();
    }
}
