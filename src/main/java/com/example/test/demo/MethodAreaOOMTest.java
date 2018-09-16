
package com.example.test.demo;

import com.example.test.demo.domain.Student;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * jdk7 方法区与常量池存在PermGen里(常量池存引用 实际数据存在堆里)
 * jdk8后 全部存在元数据区Metaspace了(常量池存引用 实际数据存在堆里)
 * <p>
 * 有效配置:
 * jdk7: -XX:PermSize=100m -XX:MaxPermSize=200m
 * jdk8: -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m
 *
 * 任何一个JVM参数的默认值可以通过-XX:+PrintFlagsFinal -version |grep JVMParamName获取，例如：-XX:+PrintFlagsFinal -version |grep MetaspaceSize
 * @author caizq
 * @date 2018/9/16
 * @since v1.0.0
 */
@Slf4j
public class MethodAreaOOMTest {
    public static void main(final String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Student.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(objects, args);
                }
            });
            enhancer.create();
        }
    }
}
