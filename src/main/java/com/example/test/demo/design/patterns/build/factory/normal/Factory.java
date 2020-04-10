package com.example.test.demo.design.patterns.build.factory.normal;

import com.example.test.demo.design.patterns.build.factory.EnumPhoneType;
import com.example.test.demo.design.patterns.build.factory.Phone;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public interface Factory {
    Phone create(EnumPhoneType phoneType);
}
