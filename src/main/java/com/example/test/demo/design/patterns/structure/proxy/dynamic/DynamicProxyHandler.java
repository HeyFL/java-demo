
package com.example.test.demo.design.patterns.structure.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object obj;

    public DynamicProxyHandler(Object obj) {
        this.obj = obj;
    }

    private void preDo() {
        System.out.println("执行前");
    }

    private void afterDo() {
        System.out.println("执行后");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preDo();
        Object result = method.invoke(obj, args);
        afterDo();
        return result;
    }
}
