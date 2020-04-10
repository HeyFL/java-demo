
package com.example.test.demo.design.patterns.behavior.command.group;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caizq
 * @date 2019/3/21
 * @since v1.0.0
 */
@Slf4j
public class WindowsSystemGroup implements SystemGroup {
    @Override
    public void readFromNet() {
        System.out.println("windows 读取数据");
    }

    @Override
    public void writeIntoDisk() {
        System.out.println("windows 开始写盘");

    }
}
