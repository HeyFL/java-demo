/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo;

import com.example.test.demo.domain.Student;
import com.example.test.demo.util.MethodSpendUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 实验结果
 * testReflection耗时3043ms
 * testReflection耗时1964ms
 * testNormal耗时215ms
 * getReflectionMethod耗时199332ms
 * <p>
 * 5亿次调用  也就只需要2s时间，平均起来耗时可以忽略
 * 但是通过反射getMethod的效率比较低，如果是高并发使用的时候  可以把方法对象保存下来
 * <p>
 * 结论:
 * 大胆使用反射,快得很,管他并发高不高
 *
 * @author caizq
 * @date 2018/9/17
 * @since v1.0.0
 */
@Slf4j
public class ReflectionTest {
    private static Student student = new Student();
    private static Class clazz = Student.class;
    /**
     * n * 一千万次
     */
    private static long tryCount = 5000 * 1000000;

    static {
        //student.setSex("asd");

    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = clazz.getDeclaredMethod("getSex");

        MethodSpendUtil.spendMs(new ReflectionTest(), "testReflection", method);
        method.setAccessible(true);
        MethodSpendUtil.spendMs(new ReflectionTest(), "testReflection", method);

        MethodSpendUtil.spendMs(new ReflectionTest(), "testNormal");
        MethodSpendUtil.spendMs(new ReflectionTest(), "getReflectionMethod");
    }

    private static void testReflection(Method declaredMethod) throws IllegalAccessException, InvocationTargetException {
        for (int i = 0; i < tryCount; i++) {
            declaredMethod.invoke(student);
        }
    }

    private void testNormal() {
        for (int i = 0; i < tryCount; i++) {
            student.getSex();
        }
    }

    private void getReflectionMethod() throws NoSuchMethodException {
        for (int i = 0; i < tryCount; i++) {
            clazz.getDeclaredMethod("getSex");
        }
    }
}
