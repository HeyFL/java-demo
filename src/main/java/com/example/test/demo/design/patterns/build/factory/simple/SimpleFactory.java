
package com.example.test.demo.design.patterns.build.factory.simple;

import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.HuaWeiPhone;
import com.example.test.demo.design.patterns.build.factory.Phone;
import com.example.test.demo.design.patterns.build.factory.XiaoMiPhone;

/**
 * 简单工厂/静态工厂方法
 *
 * 1个工厂，生成1类产品
 * 如：生产【小米手机】和【华为手机】
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class SimpleFactory {
    private volatile static SimpleFactory instance;

    private SimpleFactory() {
    }

    public static SimpleFactory getInstance() {
        if (instance == null) {
            synchronized (SimpleFactory.class) {
                if (instance == null) {
                    instance = new SimpleFactory();
                    return instance;
                }
            }
        }
        return instance;
    }

    public Phone create(EnumPhoneType phoneType) {
        switch (phoneType) {
            case MI:
                return new XiaoMiPhone();
            case HUA:
                return new HuaWeiPhone();
            default:
                throw new RuntimeException();
        }

    }
}
