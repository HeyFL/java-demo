/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.build.factory.abstract1;

import com.example.test.demo.design.patterns.build.factory.AirPods1;
import com.example.test.demo.design.patterns.build.factory.AirPods2;
import com.example.test.demo.design.patterns.build.factory.EnumHeadsetType;
import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Headset;
import com.example.test.demo.design.patterns.build.factory.HuaWeiPhone;
import com.example.test.demo.design.patterns.build.factory.Phone;
import com.example.test.demo.design.patterns.build.factory.XiaoMiPhone;
import com.example.test.demo.design.patterns.build.factory.simple.SimpleFactory;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class HighClassFactory implements AbstractFactory {
    private volatile static HighClassFactory instance;

    private HighClassFactory() {
    }

    public static HighClassFactory getInstance() {
        if (instance == null) {
            synchronized (SimpleFactory.class) {
                if (instance == null) {
                    instance = new HighClassFactory();
                    return instance;
                }
            }
        }
        return instance;
    }

    @Override
    public Phone createPhone(EnumPhoneType phoneType) {
        switch (phoneType) {
            case MI:
                XiaoMiPhone xiaoMiPhone = new XiaoMiPhone();
                xiaoMiPhone.setExtStr("高档");
                return xiaoMiPhone;
            case HUA:
                HuaWeiPhone huaWeiPhone = new HuaWeiPhone();
                huaWeiPhone.setExtStr("高档");
                return huaWeiPhone;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public Headset createHeadset(EnumHeadsetType headsetType) {
        switch (headsetType) {
            case AIRPOD1:
                AirPods1 airPods1 = new AirPods1();
                airPods1.setExtStr("高档");
                return airPods1;
            case AIRPOD2:
                AirPods2 airPods2 = new AirPods2();
                airPods2.setExtStr("高档");
                return airPods2;
            default:
                throw new RuntimeException();
        }
    }
}
