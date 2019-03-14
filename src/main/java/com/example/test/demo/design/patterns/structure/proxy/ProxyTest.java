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
public class ProxyTest {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.saySth("Chris");
    }
}
