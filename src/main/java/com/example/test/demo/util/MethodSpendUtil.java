
package com.example.test.demo.util;

import jodd.util.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author caizq
 * @date 2018/8/28
 * @since v1.0.0
 */
public class MethodSpendUtil {

    public static Object spendMs(Object obj, String method, Object... args) throws InvocationTargetException, IllegalAccessException {
        if ((StringUtil.isEmpty(method))) {
            throw new RuntimeException();
        }
        Method armMethod  =getArmMethod(obj, method);

        //反射调用  耗时计算(这里会把反射时间也包含进去)
        long startTime = System.currentTimeMillis();
        Object result = armMethod.invoke(obj,args);
        System.out.println(armMethod.getName()+"耗时"+(System.currentTimeMillis()-startTime)+"ms");
        return result;
    }

    private static Method getArmMethod(Object obj, String method) {

        Class clazz =obj.getClass();
        Method[] clazzMethods = clazz.getDeclaredMethods();
        for (Method clazzMethod : clazzMethods) {
            if (clazzMethod.getName().equalsIgnoreCase(method)) {
                clazzMethod.setAccessible(true);
                return clazzMethod;
            }
        }
        throw new RuntimeException();
    }
}
