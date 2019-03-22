
package com.example.test.demo.jvm.g1;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 测试字符串去重(仅在≥JDK8时起效)
 * -XX:+UseG1GC -XX:+UseStringDeduplication
 *
 * @author caizq
 * @date 2018/10/8
 * @since v1.0.0
 */
@Slf4j
public class StringDeduplicationTest {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        while (true) {
            list.add(new String("abc"));
        }
    }
}
