package com.example.test.demo.nio.http.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.net.ssl.SSLEngine;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author caojiancheng
 * @date 2021/4/26
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HttpResponseVO extends BaseNetVO {

    /**
     * 协议
     */
    private String protocol;

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * 状态描述
     */
    private String statusMsg;

    /**
     * 原始响应头下标
     */
    private Integer headerIndex;

    /**
     * 原始响应头
     */
    private byte[] originHeader;

    /**
     * 响应头
     */
    private LinkedHashMap<String, List<String>> headers;

    /**
     * 响应体下标
     */
    private Integer bodyIndex;

    /**
     * 响应体
     */
    private byte[] body;

    /**
     * 分段长度
     */
    private Integer chunkedNum;

    /**
     * 分段长度
     */
    private String chunked;


    /**
     * 分段在body的原始位置
     */
    private Integer chunkedInitIndex;

    /**
     * 应用数据包
     */
    private ByteBuffer appBuffer;

    /**
     * 报文数据包
     */
    private ByteBuffer packetBuffer;

    /**
     * 原始http发送报文
     */
    private HttpRequestVO httpRequestVO;

    public byte[] setGetBody(byte[] body) {
        this.body = body;
        return body;
    }

    public byte[] setGetOriginHeader(byte[] originHeader) {
        this.originHeader = originHeader;
        return originHeader;
    }

    public Integer getIncrementBodyIndex() {
        this.bodyIndex++;
        return bodyIndex - 1;
    }

    public ByteBuffer getAppBuffer(int capacity) {
        return Optional.ofNullable(appBuffer).orElseGet(() -> {
            appBuffer = ByteBuffer.allocate(capacity);
            return appBuffer;
        });
    }

    public ByteBuffer getPacketBuffer(int capacity) {
        return Optional.ofNullable(packetBuffer).orElseGet(() -> {
            packetBuffer = ByteBuffer.allocate(capacity);
            return packetBuffer;
        });
    }
}
