/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.build.factory;

import lombok.Data;

/**
 * 小米手机 价位更低
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
@Data
public class XiaoMiPhone implements Phone {
    private String extStr = "";

    @Override
    public void call() {
        System.out.println(extStr + "小米手机");
    }
}
