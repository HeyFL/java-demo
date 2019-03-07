package com.example.test.demo.jvm.happen.before;

import lombok.Data;

@Data
public class VolatileTest {
    private volatile int i;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();

        System.out.println(volatileTest.getI());
    }
}
