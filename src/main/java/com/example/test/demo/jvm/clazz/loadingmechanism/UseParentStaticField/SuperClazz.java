
package com.example.test.demo.jvm.clazz.loadingmechanism.UseParentStaticField;

/**
 * @author caizq
 * @date 2018/12/7
 * @since v1.0.0
 */

public class SuperClazz {
    public static int value = 123;

    static {
        System.out.println("Super init");
    }
}
