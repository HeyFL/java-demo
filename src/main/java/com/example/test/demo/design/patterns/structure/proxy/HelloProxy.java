/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.structure.proxy;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */

public class HelloProxy implements Subject {
    private HelloSubject helloSubject;

    public HelloProxy() {
        helloSubject = new HelloSubject();
    }

    private void preDo() {
        System.out.println("执行前");
    }

    private void afterDo() {
        System.out.println("执行后");

    }

    @Override
    public void saySth(String str) {
        preDo();
        helloSubject.saySth(str);
        afterDo();
    }
}
