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
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class DynamicProxyCreate {
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public DynamicProxyCreate() {
        HelloSubject helloSubject = new HelloSubject();
        Subject proxy = (Subject) Proxy.newProxyInstance(HelloSubject.class.getClassLoader(), new Class[]{Subject.class}, new DynamicProxyHandler(helloSubject));
        beanMap.put(Subject.class.getName(), proxy);
    }

    public Object getBean(String interfaceName) {
        return beanMap.get(interfaceName);
    }
}
