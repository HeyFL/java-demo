
package com.example.test.demo.design.patterns.build.factory;

import lombok.Data;

/**
 * AirPods2
 *
 * @author caizq
 * @date 2019/3/15
 * @since v1.0.0
 */
@Data
public class AirPods2 implements Headset {
    private String extStr = "";

    @Override
    public void heard() {
        System.out.println(extStr + "苹果 AirPods2 耳机");
    }
}
