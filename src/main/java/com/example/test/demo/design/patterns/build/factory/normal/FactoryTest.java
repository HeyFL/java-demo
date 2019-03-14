/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.build.factory.normal;

import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Phone;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class FactoryTest {
    public static void main(String[] args) {
        Factory factory = LowClassFactory.getInstance();
        Phone phone = factory.create(EnumPhoneType.MI);
        phone.call();
    }
}
