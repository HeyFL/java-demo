package com.example.test.demo.design.patterns.behavior.command.group;

/**
 * 操作系统
 *
 * @author caizq
 * @date 2019/3/21
 * @since v1.0.0
 */
public interface SystemGroup {

    /**
     * 从网络读取数据
     */
    void readFromNet();

    /**
     * 写道磁盘
     */
    void writeIntoDisk();
}
