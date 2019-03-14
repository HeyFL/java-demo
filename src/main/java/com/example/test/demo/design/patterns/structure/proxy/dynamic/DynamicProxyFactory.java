/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.design.patterns.structure.proxy.dynamic;

import com.example.test.demo.design.patterns.structure.proxy.HelloSubject;
import com.example.test.demo.design.patterns.structure.proxy.Subject;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这不是工厂模式的工厂  只是模仿Spring的bean装载、获取逻辑的工厂而已
 * 实际上，这里也可以用工厂模式进行解耦，以达到
 * 避免动态代理必须要将具体的代理实现暴露给高层模块的缺点
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class DynamicProxyFactory {
    private static Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public DynamicProxyFactory() {
        HelloSubject helloSubject = new HelloSubject();
        Subject proxy = (Subject) Proxy.newProxyInstance(HelloSubject.class.getClassLoader(), new Class[]{Subject.class}, new DynamicProxyHandler(helloSubject));
        beanMap.put(Subject.class.getName(), proxy);
    }

    public Object getBean(String interfaceName) {
        return beanMap.get(interfaceName);
    }
}
