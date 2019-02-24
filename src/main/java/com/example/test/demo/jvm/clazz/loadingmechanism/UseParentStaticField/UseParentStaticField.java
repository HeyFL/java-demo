/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.jvm.clazz.loadingmechanism.UseParentStaticField;

/**
 * 引用父类静态字段  只会初始化父类
 *
 * @author caizq
 * @date 2018/12/7
 * @since v1.0.0
 */
public class UseParentStaticField {
    public static void main(String[] args) {

        //System.out.println(SubClazz.value);
        new SubClazz();
        new SubClazz();
        new SubClazz();
    }
}
