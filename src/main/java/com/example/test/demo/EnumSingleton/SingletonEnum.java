package com.example.test.demo.EnumSingleton;

/**
 * 一般单例模式需要考虑线程安全,枚举类不需要;
 *
 * 根据枚举类特点:
 * 1.不能被 new
 * 2.JVM加载枚举类时,会生成唯一实例;
 *
 * 故,建议使用这种方式实践,代码短小精悍;
 * 无线程安全问题,无需序列化/反序列化/反射(攻击)问题
 *
 * 关于反射攻击 以及enum原理可以在这篇文章进一步学习
 * ↓↓↓
 * (https://segmentfault.com/a/1190000000699591 该链接附:反编译后的enum类)
 */
public enum SingletonEnum {
    INSTANCE;
    public void printMethod(){
        System.out.println("这是枚举类实现的单例模式");
    }
}
