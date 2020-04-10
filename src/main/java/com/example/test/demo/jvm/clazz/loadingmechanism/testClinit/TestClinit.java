
package com.example.test.demo.jvm.clazz.loadingmechanism.testClinit;

/**
 * 编译器在初始化阶段会自动收集类的所有赋值
 *
 * @author caizq
 * @date 2018/12/10
 * @since v1.0.0
 */
public class TestClinit {
    static int i = 1;

    static {
        i = 0;  // 给变量复制可以正常编译通过
        //System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }

    static {
        System.out.print(i); //输出1
    }

    public static void main(String[] args) {

    }
}
