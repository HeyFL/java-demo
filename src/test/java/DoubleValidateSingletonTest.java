package com.example.test.demo.EnumSingleton;

import com.example.test.demo.EnumSingleton.DoubleValidateSingleton;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class DoubleValidateSingletonTest {
    private static int threadNum = 100;
    private final static CountDownLatch countDownLatch = new CountDownLatch(threadNum);

    @Test
    public void testDoubleValidateSingletonInMultiThread() throws InterruptedException {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    //等待所有线程创建完成
                    countDownLatch.countDown();
                    countDownLatch.await();

                    DoubleValidateSingleton singleton = DoubleValidateSingleton.getSingleton();
                    singleton.printMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
