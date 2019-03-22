
package com.example.test.demo.design.patterns.structure.proxy;

/**
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.saySth("Chris");
    }
}
