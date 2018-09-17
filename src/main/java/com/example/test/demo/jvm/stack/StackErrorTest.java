package com.example.test.demo.jvm.stack;

import lombok.Data;

/**
 * -Xss128k
 */
@Data
public class StackErrorTest {
    private long i = 0;

    public static void main(String[] args) throws InterruptedException {
        //1.StackOverFlow
        /*new Thread(() -> {
            StackErrorTest testData = new StackErrorTest();
            try {

                testData.test1();
            } catch (Throwable e) {
                System.out.println(Thread.currentThread().getName() + " " +testData.getI());

            }
        }).start();*/


        //2.OOM
        while (true) {
            new Thread(() -> {
                StackErrorTest testData = new StackErrorTest();
                testData.test2();
            }).start();
        }

    }

    public void test1() {
        i++;
        test1();
    }

    public void test2() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
