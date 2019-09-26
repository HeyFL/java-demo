package com.example.test.demo;

import java.security.AccessControlContext;

public class SynchronizedTest implements Runnable{
    //共享资源(临界资源)
    static int i=0;
    int methodName;

    public void setMethodName(int methodName) {
        this.methodName = methodName;
    }

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase1(){
        i++;
    }
    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase2(){
        i++;
    }
    @Override
    public void run() {
        if (methodName==1) {
            for(int j=0;j<1000000;j++){
                increase1();
            }
        }else {
        for(int j=0;j<1000000;j++){
            increase1();
        }}
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest instance=new SynchronizedTest();
        Thread t1=new Thread(instance);
        instance.setMethodName(1);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}
