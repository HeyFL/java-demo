
package com.example.test.demo.jvm.clazz.loadingmechanism.UseParentStaticField;

/**
 * @author caizq
 * @date 2018/12/7
 * @since v1.0.0
 */
public class SubClazz extends SuperClazz {
    static {
        System.out.println("parent init");
    }
}
