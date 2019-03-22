
package com.example.test.demo.design.patterns.build.factory.abstract1;

import com.example.test.demo.design.patterns.build.factory.EnumHeadsetType;
import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Headset;
import com.example.test.demo.design.patterns.build.factory.Phone;

/**
 * 【N个】工厂，【每个工厂】都能生成不同的【N类】产品
 *
 * 如：说AB两个工厂，分别生产【高质量小米/华为手机】和【次品小米/华为手机】
 * 除此之外
 * 还能生产【高质量苹果耳机】和【次品苹果耳机】
 *
 * @author caizq
 * @date 2019/3/15
 * @since v1.0.0
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory instance = HighClassFactory.getInstance();
        Phone phone = instance.createPhone(EnumPhoneType.HUA);
        Headset headset = instance.createHeadset(EnumHeadsetType.AIRPOD1);
        phone.call();
        headset.heard();

    }
}
