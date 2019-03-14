/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.build.factory.simple;

import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Phone;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimpleFactory instance = SimpleFactory.getInstance();
        Phone phone = instance.create(EnumPhoneType.HUA);
        phone.call();
    }
}
