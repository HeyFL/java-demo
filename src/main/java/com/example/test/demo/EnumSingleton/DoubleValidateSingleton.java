package com.example.test.demo.EnumSingleton;

public class DoubleValidateSingleton {

    private static volatile DoubleValidateSingleton singleton;

    private DoubleValidateSingleton() {
    }

    public static DoubleValidateSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleValidateSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleValidateSingleton();
                }
            }
        }
        return singleton;
    }

    public void printMethod(){
        System.out.println("这是双重校验单例模式");
    }
}
