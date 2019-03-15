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
 * 抽象工厂  添加方法，生产新产品
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public interface AbstractFactory {
    Phone createPhone(EnumPhoneType phoneType);

    Headset createHeadset(EnumHeadsetType headsetType);
}
