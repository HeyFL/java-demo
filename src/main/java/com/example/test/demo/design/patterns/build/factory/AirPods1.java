/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.build.factory;

import lombok.Data;

/**
 * AirPods1
 *
 * @author caizq
 * @date 2019/3/15
 * @since v1.0.0
 */
@Data
public class AirPods1 implements Headset {
    private String extStr = "";

    @Override
    public void heard() {
        System.out.println(extStr + "苹果 AirPods1 耳机");
    }
}
