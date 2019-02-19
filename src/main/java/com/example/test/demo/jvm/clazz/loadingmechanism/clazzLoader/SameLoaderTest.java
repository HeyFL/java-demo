/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.jvm.clazz.loadingmechanism.clazzLoader;

import com.example.test.demo.domain.Student;

/**
 * @author caizq
 * @date 2018/12/28
 * @since v1.0.0
 */
public class SameLoaderTest {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(Student.class.getClassLoader());
    }
}
