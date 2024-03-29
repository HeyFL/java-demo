package com.example.test.demo.nio.http.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author caojiancheng
 * @date 2021/4/26
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HttpRequestVO extends BaseNetVO {

    /**
     * 请求端口
     */
    private Integer port;

    /**
     * 请求地址
     */
    private String host;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 请求参数字符串
     */
    private String path;

    /**
     * 请求头
     */
    private LinkedHashMap headers;

    /**
     * 请求体
     */
    private byte[] body;


    /**
     * 写入的ByteBuffer
     */
    private ByteBuffer byteBuffer;

    /**
     * ssl引擎
     */
    private SSLEngine sslEngine;
}
