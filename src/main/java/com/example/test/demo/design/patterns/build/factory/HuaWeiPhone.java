
package com.example.test.demo.design.patterns.build.factory;

import lombok.Data;

/**
 * 华为手机
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
@Data
public class HuaWeiPhone implements Phone {
    private String extStr = "";

    @Override
    public void call() {
        System.out.println(extStr + "华为手机");
    }
}
