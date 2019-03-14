/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.structure.proxy.dynamic;

import com.example.test.demo.design.patterns.structure.proxy.Subject;

/**
 * 动态代理个人最佳实践测试
 * <p>
 * 与静态代理区别为:
 * 需要由创建方告诉动态代理实现类
 * <p>
 * spring知道实现类是哪个   所以可以通过动态代理创建各种代理类
 * 然后缓存下来   给调用方get使用
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        DynamicProxyCreate dynamicProxyCreate = new DynamicProxyCreate();
        Subject subject = (Subject) dynamicProxyCreate.getBean(Subject.class.getName());
        subject.saySth("动态代理");
    }
}
