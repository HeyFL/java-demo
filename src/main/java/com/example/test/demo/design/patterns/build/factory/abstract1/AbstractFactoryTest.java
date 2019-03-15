/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.build.factory.abstract1;

import com.example.test.demo.design.patterns.build.factory.EnumHeadsetType;
import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Headset;
import com.example.test.demo.design.patterns.build.factory.Phone;

/**
 * @author caizq
 * @date 2019/3/15
 * @since v1.0.0
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory instance = HighClassFactory.getInstance();
        Phone phone = instance.createPhone(EnumPhoneType.HUA);
        Headset headset = instance.createHeadset(EnumHeadsetType.AIRPOD1);
        phone.call();
        headset.heard();

    }
}
