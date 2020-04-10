
package com.example.test.demo.design.patterns.build.factory.normal;

import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Phone;

/**
 * 【N个】工厂，【每个工厂】都能生成不同的【1类】产品
 *
 * 如：说AB两个工厂，分别生产【高质量小米/华为手机】和【次品小米/华为手机】
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class FactoryTest {
    public static void main(String[] args) {
        Factory factory = LowClassFactory.getInstance();
        Phone phone = factory.create(EnumPhoneType.MI);
        phone.call();
    }
}
